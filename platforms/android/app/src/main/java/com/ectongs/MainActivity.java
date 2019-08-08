/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.ectongs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ectongs.app.EctongsApplication;
import com.ectongs.device.printer.Printer;
import com.ectongs.device.printer.common.Constant;
import com.ectongs.device.printer.receiver.UsbBroadCastReceiver;
import com.printsdk.usbsdk.UsbDriver;

import org.apache.cordova.CordovaActivity;

import cn.wch.ch34xuartdriver.CH34xUARTDriver;

public class MainActivity extends CordovaActivity {

    private String url = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        EctongsApplication application = (EctongsApplication) this.getApplication();

        url = sharedPreferences.getString((String) this.getResources().getText(R.string.setting_url_key), "");


        UsbManager manager = (UsbManager) this.getSystemService(Context.USB_SERVICE);
        UsbDriver driver = new UsbDriver(manager, this);

        PendingIntent intent = PendingIntent.getBroadcast(this, 0, new Intent(Constant.ACTION_USB_PERMISSION), 0);
        driver.setPermissionIntent(intent);

        //注册Usb设备监听广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);

        UsbBroadCastReceiver receiver = new UsbBroadCastReceiver(driver);

        this.registerReceiver(receiver, filter);

        if (!openPrintDevice(manager, driver)) {
            Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(this.getResources().getText(R.string.alert_title))
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("您的设备不支持USB打印，是否停止程序？")
                    .setPositiveButton(this.getResources().getText(R.string.alert_confirm), (dialog1, which) -> exitApp())
                    .setNegativeButton(this.getResources().getText(R.string.alert_cancel), (dialog1, which) -> {
                    })
                    .create();

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
//            Toast.makeText(this, "USB打印设备: 打开设备失败，即将退出", Toast.LENGTH_SHORT).show();
//            this.finish();
        }

        application.setDriver(driver);
        application.setPrinter(new Printer(driver));


        //初始化USB转串口设备
        if (!initUARTDriver(application)) {
            Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(this.getResources().getText(R.string.alert_title))
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("您的设备不支持USB HOST，是否停止程序？")
                    .setPositiveButton(this.getResources().getText(R.string.alert_confirm), (dialog1, which) -> exitApp())
                    .setNegativeButton(this.getResources().getText(R.string.alert_cancel), (dialog1, which) -> {
                    })
                    .create();

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }


//        //跳转到相应页面
        if (!url.isEmpty()) {
            loadUrl(url);
            Toast.makeText(MainActivity.this, "即将跳转至：" + url, Toast.LENGTH_LONG).show();
        } else {
            //打开输入网址窗口
            final EditText editText = new EditText(this);
            new AlertDialog.Builder(this).setTitle(this.getResources().getText(R.string.setting_url_title))
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setView(editText)
                    .setPositiveButton(this.getResources().getText(R.string.setting_url_ok), (dialog, which) -> {
                        String url = editText.getText().toString();
                        sharedPreferences
                                .edit()
                                .putString((String) this.getResources().getText(R.string.setting_url_key), url)
                                .apply();
                        loadUrl(url);
                        Toast.makeText(MainActivity.this, "即将跳转至：" + url, Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton(this.getResources().getText(R.string.setting_url_cancel), (dialog, which) -> exitApp()).show();
        }


    }


    private void exitApp() {
        appView.postMessage("exit", null);

        this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EctongsApplication application = (EctongsApplication) this.getApplication();
        application.getUart().CloseDevice();
    }

    private boolean openPrintDevice(UsbManager manager, UsbDriver driver) {
        if (!driver.isConnected()) {
            for (UsbDevice device : manager.getDeviceList().values()) {
                if (device.getProductId() == Constant.PID11 && device.getVendorId() == Constant.VENDORID) {

                    if (driver.usbAttached(device)) {
                        Toast.makeText(this, "USB打印设备: 已建立连接", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "USB打印设备: 无法建立连接", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (driver.openUsbDevice(device)) {
                        Toast.makeText(this, "USB打印设备: 已打开设备", Toast.LENGTH_SHORT).show();
                        if (device.getProductId() == Constant.PID11) {
                            if (manager.hasPermission(device)) {
                                Toast.makeText(this, "USB打印设备: 已获取权限", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "USB打印设备: 无法获取权限", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    } else {
                        Toast.makeText(this, "USB打印设备: 无法打开设备", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 初始化UART 设备
     *
     * @param application
     * @return
     */
    public boolean initUARTDriver(EctongsApplication application) {
        CH34xUARTDriver uartDriver = new CH34xUARTDriver(
                (UsbManager) getSystemService(Context.USB_SERVICE),
                this,
                com.ectongs.device.serial.common.Constant.ACTION_USB_PERMISSION);

        if (!uartDriver.UsbFeatureSupported()) {
            return false;
        }

        int reval = uartDriver.ResumeUsbPermission();
        if (reval != 0) {
            return false;
        }

        reval = uartDriver.ResumeUsbList();
        if (reval != 0) {
            uartDriver.CloseDevice();
            return false;
        }

        if (uartDriver.mDeviceConnection == null || !uartDriver.UartInit()) {
            return false;
        }

        uartDriver.SetConfig(com.ectongs.device.serial.common.Constant.baudRate,
                com.ectongs.device.serial.common.Constant.dataBit,
                com.ectongs.device.serial.common.Constant.stopBit,
                com.ectongs.device.serial.common.Constant.parity,
                com.ectongs.device.serial.common.Constant.flowControl);

        application.setUart(uartDriver);
        return true;
    }
}
