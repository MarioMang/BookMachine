package com.ectongs.plugin;

import android.util.Log;

import com.ectongs.app.EctongsApplication;
import com.ftdi.j2xx.FT_Device;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

public class UartPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        EctongsApplication app = (EctongsApplication) cordova.getActivity().getApplication();
//        CH34xUARTDriver driver = app.getUart();
        FT_Device device = app.getSerialDevice();

        String str = args.getString(0);

        boolean result = false;

        if ("write".equals(action)) {
            if (device != null) {
                if (str != null) {
                    byte bytes[] = str.getBytes();
                    int n = device.write(bytes, bytes.length);
                    Log.d("UartPlugin", "成功发送消息【" + str + "】 -> 返回[" + n + "]个字节");
                    callbackContext.success("成功发送消息【" + str + "】 -> 返回" + n + "个字节");
                    result = true;
                }
            } else {
                callbackContext.error("设备不能为空");
            }
        }
        callbackContext.error("error");
        return result;
    }

}
