package com.ectongs.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if ("send".equals(action)) {
            try {
                sendSocketMessage(args.getString(0), args.getInt(1), args.getString(2));
                callbackContext.success("success");
            } catch (IOException e) {
                callbackContext.error(e.getMessage());
                return false;
            }
        }
        return true;
    }


    public void sendSocketMessage(String host, int port, String message) throws IOException {

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(60000);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")));

        pw.print(message);
        pw.flush();
        socket.close();

    }
}
