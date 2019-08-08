package com.ectongs.plugin;

import com.ectongs.app.EctongsApplication;
import com.ectongs.device.printer.Printer;
import com.ectongs.utils.StringUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

import cn.wch.ch34xuartdriver.CH34xUARTDriver;

public class UartPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        EctongsApplication app = (EctongsApplication) cordova.getActivity().getApplication();
        CH34xUARTDriver driver = app.getUart();

        boolean result = false;

        switch (action) {
            case "write":
                if (write(driver, args.getString(0))) {
                    callbackContext.success("success");
                    result = true;
                } else {
                    callbackContext.error("error");
                }
                break;
            default:
                callbackContext.error("error");
                return false;
        }
        return result;
    }

    private boolean write(CH34xUARTDriver driver, String message) {
        if (!driver.isConnected()) {
            return false;
        }
        byte[] bytes = StringUtils.toByteArray(message);
        return driver.WriteData(bytes, bytes.length) == 0;
    }


}
