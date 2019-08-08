package com.ectongs.device.printer;

import android.hardware.usb.UsbDevice;

import com.printsdk.cmd.PrintCmd;
import com.printsdk.usbsdk.UsbDriver;

public class Printer {

    private UsbDriver driver;

    public Printer(UsbDriver driver) {
        this.driver = driver;
    }

    public UsbDriver getDriver() {
        return driver;
    }

    public void setDriver(UsbDriver driver) {
        this.driver = driver;
    }

    /**
     * 设置打印机指令模式
     * @param mode 2 EPIC模式、3 EPOS模式
     * @return
     */
    public int commandMode(int mode) {
        return driver.write(PrintCmd.SetCommandmode(mode));
    }

    /**
     * 清理缓存，清除之前设置的参数
     * @return
     */
    public int clean() {
        return driver.write(PrintCmd.SetClean());
    }

    /**
     * 设置行间距
     * @param lineSpace 行间距，取值0-127，单位0.125mm
     * @return
     */
    public int lineSpace(int lineSpace) {
        return driver.write(PrintCmd.SetLinespace(lineSpace));
    }

    /**
     * 设置字符间距
     * @param charSpace 字符间距，取值0-64，单位0.125mm
     * @return
     */
    public int asciiSpace(int charSpace) {
        return driver.write(PrintCmd.SetSpacechar(charSpace));
    }

    /**
     * 设置汉字间距
     * @param chineseLeftSpace 汉字左空，取值0-64，单位0.125mm
     * @param chineseRightSpace 汉字右空，取值0-64，单位0.125mm
     * @return
     */
    public int chineseSpace(int chineseLeftSpace, int chineseRightSpace) {
        return driver.write(PrintCmd.SetSpacechinese(chineseLeftSpace, chineseRightSpace));
    }

    /**
     * 设置左边界
     * @param marginLeft 字符间距，取值0-576，单位0.125mm
     * @return
     */
    public int marginLeft(int marginLeft) {
        return driver.write(PrintCmd.SetLeftmargin(marginLeft));
    }

    /**
     * 设置字符右侧的间距为n 个水平点距。 在倍宽模式下，字符右侧间距是正常 值的两倍;当字符被放大时，
     * 字符右侧间距被放大同样的倍数
     * @param marginRight 右边距[范围] 0 ≤ n ≤ 255
     * @return
     */
    public int marginRight(int marginRight) {
        return driver.write(PrintCmd.SetRightmargin(marginRight));
    }

    /**
     * 设置黑标定位偏移量
     * @param offset 偏移量，取值0-1600
     * @return
     */
    public int markCutOffset(int offset) {
        return driver.write(PrintCmd.SetMarkoffsetcut(offset));
    }

    /**
     * 设置黑标定位偏移量
     * @param offset 偏移量，取值0-1600
     * @return
     */
    public int markPrintOffset(int offset) {
        return driver.write(PrintCmd.SetMarkoffsetprint(offset));
    }

    /**
     * 设置汉字格式
     * @param width 倍宽 0 无效 1 有效
     * @param height 倍高 0 无效 1 有效
     * @param underLine 下划线 0 无效 1 有效
     * @param type 汉字字形 0 24*24 1 16*16
     * @return
     */
    public int chineseScale(int width, int height, int underLine, int type) {
        return driver.write(PrintCmd.SetSizechinese(height, width, underLine, type));
    }

    /**
     * 设置字符格式
     * @param width 倍宽 0 无效 1 有效
     * @param height 倍高 0 无效 1 有效
     * @param underLine 下划线 0 无效 1 有效
     * @param type ASCII字形 0 12*24 1 9*17
     * @return
     */
    public int asciiScale(int width, int height, int underLine, int type) {
        return driver.write(PrintCmd.SetSizechar(height, width, underLine, type));
    }

    /**
     * 设置文本放大
     * @param width 放大宽度，取值(1-8)
     * @param height 放大高度，取值(1-8)
     * @return
     */
    public int textScale(int width, int height) {
        return driver.write(PrintCmd.SetSizetext(height, width));
    }

    /**
     * 设置字符对齐
     * @param align 0 左、1 居中、2 右
     * @return
     */
    public int alignment(int align) {
        return driver.write(PrintCmd.SetAlignment(align));
    }

    /**
     * 设置字体加粗
     * @param bold 0 不加粗、1 加粗
     * @return
     */
    public int bold(int bold) {
        return driver.write(PrintCmd.SetBold(bold));
    }

    /**
     * 设置字体旋转
     * @param rotate 0 解除旋转、1 顺时针度旋转90
     * @return
     */
    public int rotate(int rotate) {
        return driver.write(PrintCmd.SetRotate(rotate));
    }


    /**
     * 设置字体方向
     * @param direction 0 左至右、1 旋转180度
     * @return
     */
    public int direction(int direction) {
        return driver.write(PrintCmd.SetDirection(direction));
    }

    /**
     * 设置反白
     * @param mode 0 取消反白;1 设置反白
     * @return
     */
    public int whiteMode(int mode) {
        return driver.write(PrintCmd.SetWhitemodel(mode));
    }

    /**
     * 设置斜体
     * @param italic 0 取消斜体;1 设置斜体
     * @return
     */
    public int italic(int italic) {
        return driver.write(PrintCmd.SetItalic(italic));
    }

    /**
     * 设置下划线(字符，ASCII 都有效)
     * @param underline 0 无 1 一个点下划线 2 两个点下划线 其他无效
     * @return
     */
    public int underLine(int underline) {
        return driver.write(PrintCmd.SetUnderline(underline));
    }

    /**
     * 设置汉字模式有无效
     * @param mode 0 进入汉字模式;1 退出汉字模式
     * @return
     */
    public int chineseMode(int mode) {
        return driver.write(PrintCmd.SetReadZKmode(mode));
    }

    /**
     * 设置水平制表位置
     * @param position 水平制表的位置 从小到大 单位一个ASCII字符 不能为0
     * @return
     */
    public int horizontalTabPosition(byte[] position) {
        return driver.write(PrintCmd.SetHTseat(position, position.length));
    }

    /**
     * 设置区域国家和代码页
     * @param country 区域国家
     * @param pageCode 代码页
     * @return
     */
    public int countryAndPageCode(int country, int pageCode) {
        return driver.write(PrintCmd.SetCodepage(country, pageCode));
    }

    /**
     * 设置NV位图
     * @param count 位图数量(单个文件最大64K，所有文件最大192K)
     * @param paths 图像文件路径(若只有文件名则使用当前路径，若指定全路径则使用指定的路径)， 以”;”分隔，个数需和iNums参数一致
     * @return
     */
    public int NVBmp(int count, String paths) {
        return driver.write(PrintCmd.SetNvbmp(count, paths));
    }

    /**
     * 打印条形码时，根据iAlign可选值进行条码对齐
     * @param align 对齐类型，0 左对齐 、1 居中对齐、2 右对齐
     * @return
     */
    public int barCodeAlign(int align) {
        return driver.write(PrintCmd.Set1DBarCodeAlign(align));
    }

    /**
     * 自检页
     * @return
     */
    public int selfCheck() {
        return driver.write(PrintCmd.PrintSelfcheck());
    }

    /**
     * 走纸，单位字符行
     * @param line 走纸行数
     * @return
     */
    public int paperSkip(int line) {
        return driver.write(PrintCmd.PrintFeedline(line));
    }

    /**
     * 走纸，单位点0.125mm
     * @param line 范围 0-250
     * @return
     */
    public int paperSkipDot(int line) {
        return driver.write(PrintCmd.PrintFeedDot(line));
    }

    /**
     * 打印字符串
     * @param str 打印的字符串内容
     * @param lineFeed 是否加换行指令0x0a: 0 加换行指令 1 不加换行指令(等到下一个换行指令才打 印)
     * @return
     */
    public int print(String str, int lineFeed) {
        return driver.write(PrintCmd.PrintString(str, lineFeed));
    }

    /**
     * 打印内容并换行，无打印内容的时候走一空白行
     * @return
     */
    public int println() {
        return driver.write(PrintCmd.PrintChargeRow());
    }

    /**
     * 执行到下一个水平制表位置
     * @return
     */
    public int toNextHorizontalTab() {
        return driver.write(PrintCmd.PrintNextHT());
    }

    /**
     * 切纸
     * @param mode 0 全切、1 半切
     * @return
     */
    public int cutPaper(int mode) {
        return driver.write(PrintCmd.PrintCutpaper(mode));
    }

    /**
     * 黑标模式下检测黑标，停止在黑标位置
     * @return
     */
    public int markPosition() {
        return driver.write(PrintCmd.PrintMarkposition());
    }

    /**
     * 黑标模式下检测黑标并进纸到打印位置(偏移量打印影响走纸距离)
     * @return
     */
    public int markPositionPrint() {
        return driver.write(PrintCmd.PrintMarkpositionprint());
    }

    /**
     * 黑标模式下检测黑标并进纸到切纸位置(偏移量切纸影响走纸距离)
     * @return
     */
    public int markPositionCut() {
        return driver.write(PrintCmd.PrintMarkpositioncut());
    }

    /**
     * 黑标切纸
     * @param mode 0 检测黑标全切、1 不检测黑标半切
     * @return
     */
    public int markCutPaper(int mode) {
        return driver.write(PrintCmd.PrintMarkcutpaper(mode));
    }

    /**
     * 打印QR码
     * @param data 内容
     * @param marginLeft 左边距，取值0-27 单位mm
     * @param unit 单位长度，即QR码大小，取值1-8，(有些打印机型只支持1-4)
     * @param round 环绕模式，0环绕(混排，有些机型不支持)、1立即打印(不混排)
     * @return
     */
    public int printQRCode(String data, int marginLeft, int unit, int round) {
        return driver.write(PrintCmd.PrintQrcode(data, marginLeft, unit, round));
    }

    /**
     * 打印QR码
     * @param data 内容
     * @param marginLeft 左边距，取值0-27 单位mm
     * @param unit 单位长度，即QR码大小，取值1-8，(有些打印机型只支持1-4)
     * @param round 环绕模式，0环绕(混排，有些机型不支持)、1立即打印(不混排)
     * @return
     */
    public int printQRCode51(String data, int marginLeft, int unit, int round) {
        return driver.write(PrintCmd.PrintQrcode51(data, marginLeft, unit, round));
    }

    /**
     * 专用T500II主板，打印二维码
     * @param data 内容
     * @param unit 单位长度，即QR码大小，取值1-9
     * @return
     */
    public int printQRCode(String data, int unit) {
        return driver.write(PrintCmd.PrintQrCodeT500II(unit, data));
    }

    /**
     * 打印PDF417码
     * @param usbDriver
     * @param width
     * @param height
     * @param row
     * @param col
     * @param data
     * @return
     */
    public int printPDF417(UsbDriver usbDriver, int width, int height, int row, int col, String data) {
        return driver.write(PrintCmd.PrintPdf417(usbDriver, width, height, row, col, data));
    }

    /**
     * 设置PDF417宽和高度
     * @param width 宽度，取值:大于等于2或者小于等于6
     * @param height 高度，取值0-255
     * @return
     */
    public int PDF417(int width, int height) {
        return driver.write(PrintCmd.setPdf417(width, height));
    }

    /**
     * 打印PDF417
     * @param row 行数
     * @param col 列数
     * @param data 内容
     * @return
     */
    public int printPdf417(int row, int col, String data) {
        return driver.write(PrintCmd.PrintPdf417(row, col, data));
    }

    /**
     * 打印条码
     * @param data 条码内容
     * @param width 条码宽度，取值2-6
     * @param height 条码高度，取值1-255
     * @param charType 条码显示字符字型 0 12*24 1 9*17
     * @param position 条码显示字符位置 0无 1上 2下 3上下
     * @param codeType * UPC-A 0,* UPC-E 1,* EAN13 2,* EAN8 3, * CODE39 4,
     *                 * ITF 5,* CODABAR 6,* Standard EAN13 7, * Standard EAN8 8
     *                 * CODE93 9,* CODE128 10
     * @return
     */
    public int printBarCode(String data, int width, int height, int charType, int position, int codeType) {
        return driver.write(PrintCmd.Print1Dbar(width, height, charType, position, codeType, data));
    }

    /**
     * 打印磁盘BMP文件，仅支持单色BMP文件
     * @param source 文件路径
     * @return
     */
    public int printBmp(String source) {
        return driver.write(PrintCmd.PrintDiskbmpfile(source));
    }

    /**
     * 支持图片内容为黑白两色的 BMP/JPG/PNG格式的文件打印
     * @param pixels 图片的像素数组
     * @param width 宽度
     * @param height 高度
     * @return
     */
    public int printImage(int[] pixels, int width, int height) {
        return driver.write(PrintCmd.PrintDiskImagefile(pixels, width, height));
    }

    /**
     * 打印NV BMP文件，仅支持单色BMP文件
     * @param nvIndex NV位图索引
     * @param mode 48 普通、49 倍宽、50 倍高、51 倍宽倍高(4倍大小)
     * @return
     */
    public int printNVBmp(int nvIndex, int mode) {
        return driver.write(PrintCmd.PrintNvbmp(nvIndex, mode));
    }

    /**
     * 获取打印机状态
     * @return
     */
    public int printerStatus() {
        return 0;
    }
}
