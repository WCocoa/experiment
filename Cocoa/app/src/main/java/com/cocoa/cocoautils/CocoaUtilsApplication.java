package com.cocoa.cocoautils;

import android.app.Application;
import android.content.pm.PackageManager;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/11/14.
 */

public class CocoaUtilsApplication extends Application {
    public static int versionCode;
    @Override
    public void onCreate() {
        super.onCreate();
        getVersionInfo();
    }

    private void getVersionInfo() {
        try {
            versionCode = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
