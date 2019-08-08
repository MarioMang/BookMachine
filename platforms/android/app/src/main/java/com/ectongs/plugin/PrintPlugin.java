package com.ectongs.plugin;

import android.widget.Toast;

import com.ectongs.app.EctongsApplication;
import com.ectongs.device.printer.Printer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

public class PrintPlugin extends CordovaPlugin {


    public void showMessage(String str1, String str2) {
        System.out.println(str1 + " : " + str2);
        Toast.makeText(cordova.getActivity(), str1 + " : " + str2, Toast.LENGTH_LONG);

    }

    @Override
    public Boolean shouldAllowBridgeAccess(String url) {
        return true;
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        EctongsApplication app = (EctongsApplication) cordova.getActivity().getApplication();
        Printer printer = app.getPrinter();

        switch (action) {
            case "showMessageFunc":
                showMessage(args.getString(0), args.getString(1));
                callbackContext.success("success");
                break;
            case "commandMode":
                callbackContext.success(printer.commandMode(args.getInt(0)));
                break;
            case "clean":
                callbackContext.success(printer.clean());
                break;
            case "lineSpace":
                callbackContext.success(printer.lineSpace(args.getInt(0)));
                break;
            case "asciiSpace":
                callbackContext.success(printer.asciiSpace(args.getInt(0)));
                break;
            case "chineseSpace":
                callbackContext.success(printer.chineseSpace(args.getInt(0), args.getInt(1)));
                break;
            case "marginLeft":
                callbackContext.success(printer.marginLeft(args.getInt(0)));
                break;
            case "marginRight":
                callbackContext.success(printer.marginRight(args.getInt(0)));
                break;
            case "markCutOffset":
                callbackContext.success(printer.markCutOffset(args.getInt(0)));
                break;
            case "markPrintOffset":
                callbackContext.success(printer.markPrintOffset(args.getInt(0)));
                break;
            case "chineseScale":
                callbackContext.success(printer.chineseScale(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3)));
                break;
            case "asciiScale":
                callbackContext.success(printer.asciiScale(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3)));
                break;
            case "textScale":
                callbackContext.success(printer.textScale(args.getInt(0), args.getInt(1)));
                break;
            case "alignment":
                callbackContext.success(printer.alignment(args.getInt(0)));
                break;
            case "bold":
                callbackContext.success(printer.bold(args.getInt(0)));
                break;
            case "rotate":
                callbackContext.success(printer.rotate(args.getInt(0)));
                break;
            case "direction":
                callbackContext.success(printer.direction(args.getInt(0)));
                break;
            case "whiteMode":
                callbackContext.success(printer.whiteMode(args.getInt(0)));
                break;
            case "italic":
                callbackContext.success(printer.italic(args.getInt(0)));
                break;
            case "underLine":
                callbackContext.success(printer.underLine(args.getInt(0)));
                break;
            case "chineseMode":
                callbackContext.success(printer.chineseMode(args.getInt(0)));
                break;
            case "horizontalTabPosition":
                callbackContext.success(printer.horizontalTabPosition(args.getArrayBuffer(0)));
                break;
            case "countryAndPageCode":
                callbackContext.success(printer.countryAndPageCode(args.getInt(0), args.getInt(1)));
                break;
            case "NVBmp":
                callbackContext.success(printer.NVBmp(args.getInt(0), args.getString(1)));
                break;
            case "barCodeAlign":
                callbackContext.success(printer.barCodeAlign(args.getInt(0)));
                break;
            case "selfCheck":
                callbackContext.success(printer.selfCheck());
                break;
            case "paperSkip":
                callbackContext.success(printer.paperSkip(args.getInt(0)));
                break;
            case "paperSkipDot":
                callbackContext.success(printer.paperSkipDot(args.getInt(0)));
                break;
            case "print":
                callbackContext.success(printer.print(args.getString(0), args.getInt(1)));
                break;
            case "println":
                callbackContext.success(printer.println());
                break;
            case "toNextHorizontalTab":
                callbackContext.success(printer.toNextHorizontalTab());
                break;
            case "cutPaper":
                callbackContext.success(printer.cutPaper(args.getInt(0)));
                break;
            case "markPosition":
                callbackContext.success(printer.markPosition());
                break;
            case "markPositionPrint":
                callbackContext.success(printer.markPositionPrint());
                break;
            case "markPositionCut":
                callbackContext.success(printer.markPositionCut());
                break;
            case "markCutPaper":
                callbackContext.success(printer.markCutPaper(args.getInt(0)));
                break;
            case "printQRCode":
                callbackContext.success(printer.printQRCode(args.getString(0), args.getInt(1), args.getInt(2), args.getInt(3)));
                break;
            case "printQRCode51":
                callbackContext.success(printer.printQRCode51(args.getString(0), args.getInt(1), args.getInt(2), args.getInt(3)));
                break;
            case "printQrCode":
                callbackContext.success(printer.printQRCode(args.getString(0), args.getInt(1)));
                break;
            case "PDF417":
                callbackContext.success(printer.PDF417(args.getInt(0), args.getInt(0)));
                break;
            case "printPdf417":
                callbackContext.success(printer.printPdf417(args.getInt(0), args.getInt(1), args.getString(2)));
                break;
            case "printBarCode":
                callbackContext.success(printer.printBarCode(args.getString(0), args.getInt(1), args.getInt(2), args.getInt(3), args.getInt(4), args.getInt(5)));
                break;
            case "printBmp":
                callbackContext.success(printer.printBmp(args.getString(0)));
                break;
            case "printImage":
                callbackContext.success(printer.printImage((int[]) args.get(0), args.getInt(1), args.getInt(2)));
                break;
            case "printNVBmp":
                callbackContext.success(printer.printNVBmp(args.getInt(0), args.getInt(1)));
                break;
            case "printerStatus":
                callbackContext.success(printer.printerStatus());
                break;
            default:
                callbackContext.success("no function");
                return false;
        }

        return true;
    }
}
