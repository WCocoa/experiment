package com.cocoa.cocoautils.utils;

import android.util.Log;


public class AppLog {
    private static String  LOG           = "neighbourmotherlog";
    private static boolean switchSetting = true;

    // private static boolean switchSetting = false;

    public static void D(String s) {
        if (switchSetting)
            Log.i(LOG, s);
    }
}
