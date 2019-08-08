package com.ectongs.device.printer.common;

public class Constant {

    public static final String ACTION_USB_PERMISSION = "com.usb.sample.USB_PERMISSION";

    public final static int PID11 = 8211; //本产品PID
    public final static int PID13 = 8213;
    public final static int PID15 = 8215;

    public final static int VENDORID = 1305;


    /**
     * SetCommmandmode(iMode)
     * EPIC 模式
     */
    public final int MODE_EPIC = 2;
    /**
     * SetCommmandmode(iMode)
     * EPOS 模式
     */
    public final int MODE_EPOS = 3;


    /**
     * 行间距最大值
     */
    public final int MAX_LINE_SPACE = 127;

    /**
     * 行间距最小值
     */
    public final int MIN_LINE_SPACE = 0;

    /**
     * 行间距单位(mm)
     */
    public final double UNIT_LINE_SPACE = 0.125;

    /**
     * 字符间距最大值
     */
    public final int MAX_CHAR_SPACE = 64;

    /**
     * 字符间距最小值
     */
    public final int MIN_CHAR_SPACE = 0;

    /**
     * 字符间距单位(mm)
     */
    public final double UNIT_CHAR_SPACE = 0.125;

    /**
     * 左边距最大值
     */
    public final int MAX_MARGIN_LEFT = 576;

    /**
     * 左边距最小值
     */
    public final int MIN_MARGIN_LEFT = 0;

    /**
     * 左边距单位(mm)
     */
    public final double UNIT_MARGIN_LEFT = 0.125;

    /**
     * 右边距最大值
     */
    public final int MAX_MARGIN_RIGHT = 255;

    /**
     * 右边距最小值
     */
    public final int MIN_MARGIN_RIGHT = 0;

    /**
     * 黑标切纸偏移量最大值
     */
    public final int MAX_MARK_OFFSET_CUT = 1600;

    /**
     * 黑标切纸偏移量最小值
     */
    public final int MIN_MARK_OFFSET_CUT = 0;

    /**
     * 黑标打印进纸偏移量最大值
     */
    public final int MAX_MARK_OFFSET_PRINT = 1600;

    /**
     * 黑标打印进纸偏移量最小值
     */
    public final int MIN_MARK_OFFSET_PRINT = 0;


    /**
     * 有效
     */
    public final int VALID = 1;

    /**
     * 无效
     */
    public final int INVALID = 0;


    /**
     * 24 * 24 汉字字形
     */
    public final int CHINESE_TYPE_24_X_24 = 0;

    /**
     * 16 * 16 汉字字形
     */
    public final int CHINESE_TYPE_16_X_16 = 1;

    /**
     * ASCII字形 12*24
     */
    public final int ASCII_TYPE_12_X_24 = 0;

    /**
     * ASCII字形 9*17
     */
    public final int ASCII_TYPE_9_X_17 = 1;

    /**
     * 文本放大最大值
     */
    public final int MAX_CHAR_SCALE = 8;

    /**
     * 文本放大最小值
     */
    public final int MIN_CHAR_SCALE = 1;

    /**
     * 左对齐
     */
    public final int ALIGN_LEFT = 0;

    /**
     * 居中对齐
     */
    public final int ALIGN_CENTER = 1;

    /**
     * 右对齐
     */
    public final int ALIGN_RIGHT = 2;

    /**
     * 字体加粗
     */
    public final int FONT_BLOD = 1;

    /**
     * 字体不加粗
     */
    public final int FONT_NO_BLOD = 0;

    /**
     * 字体不旋转
     */
    public final int FONT_NO_ROTATE = 0;

    /**
     * 字体旋转90度
     */
    public final int FONT_ROTATE = 1;

    /**
     * 字体由左向右
     */
    public final int FONT_LEFT_RIGHT = 0;

    /**
     * 字体由右向左
     */
    public final int FONT_RIGHT_LEFT = 1;

    /**
     * 取消反白
     */
    public final int FONT_NO_WHITE_MODEL = 0;

    /**
     * 设置反白
     */
    public final int FONT_WHITE_MODEL = 1;


    /**
     * 取消斜体
     */
    public final int FONT_NO_ITALIC = 0;

    /**
     * 设置斜体
     */
    public final int FONT_ITALIC = 1;

    /**
     * 无下划线
     */
    public final int NO_UNDERLINE = 0;

    /**
     * 一个点下划线
     */
    public final int ONE_DOT_UNDERLINE = 1;

    /**
     * 两个点下划线
     */
    public final int TWO_DOT_UNDERLINE = 2;

    /**
     * 进入汉字模式
     */
    public final int ENTER_CHINESE_MODE = 0;

    /**
     * 退出汉字模式
     */
    public final int QUIT_CHINESE_MODE = 1;

    /**
     * 设置区域国家
     */
    /**
     * 美国
     */
    public final int USA = 0;
    /**
     * 法国
     */
    public final int FR = 1;
    /**
     * 德国
     */
    public final int DE = 2;
    /**
     * 英国
     */
    public final int UK = 3;
    /**
     * 丹麦I
     */
    public final int DKI = 4;
    /**
     * 瑞典
     */
    public final int SE = 5;
    /**
     * 意大利
     */
    public final int ITA = 6;
    /**
     * 西班牙
     */
    public final int ES = 7;
    /**
     * 日本
     */
    public final int JP = 8;
    /**
     * 挪威
     */
    public final int NO = 9;
    /**
     * 丹麦II
     */
    public final int DKII = 10;

    /**
     * 代码页
     */
    public final int PC437 = 0x00;
    public final int PC737 = 0x01;
    public final int PC775 = 0x02;
    public final int PC850 = 0x03;
    public final int PC852 = 0x04;
    public final int PC855 = 0x05;
    public final int PC857 = 0x06;
    public final int PC858 = 0x07;
    public final int PC860 = 0x08;
    public final int PC862 = 0x09;
    public final int PC863 = 0x0A;
    public final int PC864 = 0x0B;
    public final int PC865 = 0x0C;
    public final int PC866 = 0x0D;
    public final int PC1251 = 0x0E;
    public final int PC1252 = 0x0F;
    public final int PC1253 = 0x10;
    public final int PC1254 = 0x11;
    public final int PC1255 = 0x12;
    public final int PC1256 = 0x13;
    public final int PC1257 = 0x14;
    public final int PC928 = 0x15;
    public final int HEBREW_OLD = 0x16;
    public final int IINTEL_CHAR = 0x17;
    public final int KATAKANA = 0x18;
    public final int S00_1F = 0x19;
    public final int SPACE_PAGE = 0x1A;

    /**
     * 位图单文件最大数量(KB)
     */
    public final int MAX_BIT_SINGLE_NUM = 64;
    /**
     * 位图所有文件最大数量(KB)
     */
    public final int MAX_BIT_MULTI_NUM = 192;

    /**
     * 加换行指令
     */
    public final int LINE_FEED = 0;

    /**
     * 不加换行符
     */
    public final int NO_LINE_FEED = 1;

    /**
     * 打印细走纸最大值
     */
    public final int MAX_PRINT_FEED_DOT = 250;

    /**
     * 打印细走纸最小值
     */
    public final int MIN_PRINT_FEED_DOT = 0;

    /**
     * 全切
     */
    public final int FULL_CUT = 0;
    /**
     * 半切
     */
    public final int SEMI_CUT = 1;

    /**
     * 打印机正常
     */
    public final int PRINTER_OK = 0;
    /**
     * 打印机未连接或未通电
     */
    public final int PRINTER_UNCONNECTED = 1;
    /**
     * 打印机与调用库不匹配
     */
    public final int PRINTER_MISMATCH = 2;
    /**
     * 打印头打开
     */
    public final int PRINTER_OPENED = 3;
    /**
     * 切刀未复位
     */
    public final int PRINTER_CUTTER_NON_RESET = 4;
    /**
     * 打印头过热
     */
    public final int PRINTER_TOO_HOT = 5;
    /**
     * 黑标错误
     */
    public final int PRINTER_MARK_ERROR = 6;
    /**
     * 纸尽
     */
    public final int PRINTER_PAPER_EMPTY = 7;
    /**
     * 纸将尽
     */
    public final int PRINTER_PAPER_WILL_EMPTY = 8;
}
