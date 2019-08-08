package com.ectongs.device.serial.common;

public class Constant {

    public static final String ACTION_USB_PERMISSION = "cn.wch.wchusbdriver.USB_PERMISSION";

    /**
     * 波特率
     */
    public static final int baudRate = 0x2580;
    /**
     * 数据位
     */
    public static final byte dataBit = 0x08;
    /**
     * 停止位
     */
    public static final byte stopBit = 0x01;
    /**
     * 校验: 0 - none; 1 - add; 2 - even; 3 - mark; 4 - space
     */
    public static final byte parity = 0x00;
    /**
     * 流控制: 0 - none; 1 - cts/rts
     */
    public static final byte flowControl = 0x00;
}
