package com.cocoa.cocoautils.utils;

import android.util.Log;

/**
 * ClassName:日志
 * author: Cocoa
 * date: 2016/10/18.
 */
public class AppLog {
    private static String  LOG           = "Cocoa";
    private static boolean switchSetting = true;

    // private static boolean switchSetting = false;

    public static void D(String s) {
        if (switchSetting)
            Log.i(LOG, s);
    }
}
