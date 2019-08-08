package com.ectongs.device.printer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

import com.ectongs.device.printer.common.Constant;
import com.printsdk.usbsdk.UsbDriver;

public class UsbBroadCastReceiver extends BroadcastReceiver {

    private UsbDriver usbDriver;


    public UsbBroadCastReceiver(UsbDriver usbDriver) {
        this.usbDriver = usbDriver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) { //USB 连接事件
            if (usbDriver.usbAttached(intent)) {
                if ((device.getProductId() == Constant.PID11 && device.getVendorId() == Constant.VENDORID)
                        || (device.getProductId() == Constant.PID13 && device.getVendorId() == Constant.VENDORID)
                        || (device.getProductId() == Constant.PID15 && device.getVendorId() == Constant.VENDORID)) {
                    if (usbDriver.openUsbDevice(device)) {
                        Toast.makeText(context, "USB打印设备已连接:" + device.getProductId(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) { //USB 断开事件

            if ((device.getProductId() == Constant.PID11 && device.getVendorId() == Constant.VENDORID)
                    || (device.getProductId() == Constant.PID13 && device.getVendorId() == Constant.VENDORID)
                    || (device.getProductId() == Constant.PID15 && device.getVendorId() == Constant.VENDORID)) {
                Toast.makeText(context, "USB打印设备已断开:" + device.getProductId(), Toast.LENGTH_SHORT).show();
                usbDriver.closeUsbDevice(device);
            }
        }
    }
}
