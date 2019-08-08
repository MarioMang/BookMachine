package com.ectongs.app;

import android.app.Application;

import com.ectongs.device.printer.Printer;
import com.printsdk.usbsdk.UsbDriver;

import cn.wch.ch34xuartdriver.CH34xUARTDriver;

public class EctongsApplication extends Application {

    private UsbDriver driver;

    private Printer printer;

    private CH34xUARTDriver uart;

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

    public CH34xUARTDriver getUart() {
        return uart;
    }

    public void setUart(CH34xUARTDriver uart) {
        this.uart = uart;
    }
}
