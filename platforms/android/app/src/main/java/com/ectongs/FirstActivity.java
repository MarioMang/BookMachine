package com.ectongs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ectongs.device.printer.common.Constant;
import com.ectongs.device.printer.receiver.UsbBroadCastReceiver;
import com.printsdk.cmd.PrintCmd;
import com.printsdk.usbsdk.UsbDriver;

public class FirstActivity extends Activity {

    private static final int requestCode = 0;

    private UsbManager usbManager;
    private UsbDriver usbDriver;
    private UsbDevice usbDevice;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        usbDriver.closeUsbDevice(usbDevice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button = (Button) this.findViewById(R.id.button);
        TextView textView = (TextView) this.findViewById(R.id.textView);


        button.setOnClickListener(v -> {
            if (usbDriver.isConnected()) {
                // USB线已经连接
                for (UsbDevice device : usbManager.getDeviceList().values()) {

                    if (usbDriver.isUsbPermission()) {
                        Toast.makeText(this, "isUsbPermission print", Toast.LENGTH_SHORT).show();
                    }
                    StringBuffer sb = new StringBuffer();
                    sb.append("DeviceId:\n");
                    sb.append(device.getDeviceId());
                    sb.append("\nProductId\n");
                    sb.append(device.getProductId());
                    sb.append("\nVendorId\n");
                    sb.append(device.getVendorId());
                    sb.append("\nDeviceName\n");
                    sb.append(device.getDeviceName());
                    sb.append("\n");

                    textView.append(sb.toString());
                }

                String charPageCmd = "1D 4C 30 00 1D 57 40 02 "
                        + "20 21 22 23 24 25 26 27 28 29 2A 2B 2C 2D 2E 2F "
                        + "0A 30 31 32 33 34 35 36 37 38 39 3A 3B 3C 3D 3E "
                        + "3F 0A 40 41 42 43 44 45 46 47 48 49 4A 4B 4C 4D "
                        + "4E 4F 0A 50 51 52 53 54 55 56 57 58 59 5A 5B 5C "
                        + "5D 5E 5F 0A 60 61 62 63 64 65 66 67 68 69 6A 6B "
                        + "6C 6D 6E 6F 0A 70 71 72 73 74 75 76 77 78 79 7A "
                        + "7B 7C 7D 7E 7F 0A 80 81 82 83 84 85 86 87 88 89 "
                        + "8A 8B 8C 8D 8E 8F 0A 90 91 92 93 94 95 96 97 98 "
                        + "99 9A 9B 9C 9D 9E 9F 0A A0 A1 A2 A3 A4 A5 A6 A7 "
                        + "A8 A9 AA AB AC AD AE AF 0A B0 B1 B2 B3 B4 B5 B6 "
                        + "B7 B8 B9 BA BB BC BD BE BF 0A C0 C1 C2 C3 C4 C5 "
                        + "C6 C7 C8 C9 CA CB CC CD CE CF 0A D0 D1 D2 D3 D4 "
                        + "D5 D6 D7 D8 D9 DA DB DC DD DE DF 0A E0 E1 E2 E3 "
                        + "E4 E5 E6 E7 E8 E9 EA EB EC ED EE EF 0A F0 F1 F2 "
                        + "F3 F4 F5 F6 F7 F8 F9 FA FB FC FD FE FF 0A "
                        + "1B 64 0A";

                Toast.makeText(this, "Will print", Toast.LENGTH_SHORT).show();

                printFeedDot_Test();

            }

        });

        Button buttonClose = (Button) this.findViewById(R.id.button_close);
        buttonClose.setOnClickListener(v -> {
            Toast.makeText(this, "USB打印设备: 正在关闭", Toast.LENGTH_SHORT).show();
            usbDriver.closeUsbDevice(usbDevice);
        });
    }

    private void printSpecialData(UsbDriver usbDriver, String data) {
        byte[] label = getHexCmd(data);
        if (label.length != 0) {
            usbDriver.write(label, label.length);
            usbDriver.write(PrintCmd.PrintCutpaper(0)); // 切纸类型
            usbDriver.write(PrintCmd.SetClean()); // 缓存清理
        }
    }

    // 字符串转换16进制
    public byte[] getHexCmd(String paramString) {
        String[] paramStr = paramString.split(" ");
        byte[] arrayOfByte = new byte[paramStr.length];

        for (int j = 0; j < paramStr.length; j++) {
            if (String.valueOf(arrayOfByte[j]).length() == 4) {
                byte d1 = Byte.valueOf(String.valueOf(arrayOfByte[j])
                        .substring(0, 2));
                byte d2 = Byte.valueOf(String.valueOf(arrayOfByte[j])
                        .substring(2, 4));
                arrayOfByte[j] = Integer.decode("0x" + d1).byteValue();
                arrayOfByte[j + 1] = Integer.decode("0x" + d2).byteValue();
            }
            if (!isHexNumberRex(String.valueOf(arrayOfByte[j]))) {
                break;
            }
            arrayOfByte[j] = Integer.decode("0x" + paramStr[j]).byteValue();
        }
        return arrayOfByte;
    }

    private boolean isHexNumberRex(String str) {
        String validate = "(?i)[0-9a-f]+";
//    	String validate = "[\u4e00-\u9fa5]";
        return str.matches(validate);
    }


    private void printFeedDot_Test() {
        int r = usbDriver.write(PrintCmd.SetClean());
        Log.d("FirstActivity", String.valueOf(r));
        int feedLen = Integer.valueOf(20);
        if (feedLen <= 30) {
            int i = usbDriver.write(PrintCmd.PrintFeedDot(feedLen * 8));
            Log.d("FirstActivity", String.valueOf(i));
        } else {
            for (int i = 0; i < (feedLen / 30); i++) {
//				mUsbDriver.write(PrintCmd.PrintFeedDot(240));
                feedLen = feedLen - (feedLen / 30) * 30;
                if (feedLen < 0) {
                    int j = usbDriver.write(PrintCmd.PrintFeedDot(feedLen * 8));
                    Log.d("FirstActivity", String.valueOf(j));
                }
            }
        }
    }

}
