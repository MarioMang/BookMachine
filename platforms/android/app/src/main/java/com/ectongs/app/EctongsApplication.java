package com.ectongs.app;

import android.app.Application;

import com.ectongs.device.printer.Printer;
import com.ftdi.j2xx.FT_Device;
import com.printsdk.usbsdk.UsbDriver;

public class EctongsApplication extends Application {

    private UsbDriver driver;

    private Printer printer;

    private FT_Device serialDevice;

    public FT_Device getSerialDevice() {
        return serialDevice;
    }

    public void setSerialDevice(FT_Device serialDevice) {
        this.serialDevice = serialDevice;
    }
//    private CH34xUARTDriver uart;

    public UsbDriver getDriver() {
        return driver;
    }

    public void setDriver(UsbDriver driver) {
        this.driver = driver;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

//    public CH34xUARTDriver getUart() {
//        return uart;
//    }
//
//    public void setUart(CH34xUARTDriver uart) {
//        this.uart = uart;
//    }
}
