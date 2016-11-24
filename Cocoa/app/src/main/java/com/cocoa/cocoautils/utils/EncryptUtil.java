package com.cocoa.cocoautils.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import java.security.MessageDigest;

/**
 * ClassName:加密
 * author: Cocoa
 * date: 2016/10/18.
 */
public class EncryptUtil {
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }



    public static String md5Hex(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            // System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        String strMD5_32 = hexValue.toString();

        return strMD5_32;
    }

    /**
     * 未加盐值
     *
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            // System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        String strMD5_32 = hexValue.toString();

        return strMD5_32;
    }

    /**
     * 加盐值
     *
     * @param password
     * @return
     */
    /*
	 * public static String MD5(String password) { String salt =
	 * "wcdisopqzbnm2015"; password = md5Hex(password + salt); char[] cs = new
	 * char[48]; for (int i = 0; i < 48; i += 3) { cs[i] = password.charAt(i / 3
	 * * 2); char c = salt.charAt(i / 3); cs[i + 1] = c; cs[i + 2] =
	 * password.charAt(i / 3 * 2 + 1); } return new String(cs); }
	 */

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return MD5(password + salt).equals(new String(cs1));
    }


    /**
     * 校验手机号码
     *
     * @param mobiles
     * @return
     */

    public static boolean isMobileNumber(String mobiles) {

        String telRegex = "[1]\\d{10}";//
        if (mobiles != null && mobiles.length() == 11 && mobiles.matches(telRegex)) {

            return true;
        } else
            return false;
    }

    public static int dip2px(Context context, int value) {
        float scaleing = context.getResources().getDisplayMetrics().density;
        return (int) (value * scaleing + 0.5f);
    }

    public static int px2dip(Context context, int value) {
        float scaling = context.getResources().getDisplayMetrics().density;
        return (int) (value / scaling + 0.5f);
    }

    // ："^\w+$

    /**
     * 性别
     */
    public static String getSex(int sex) {

        String sexString = "";
        switch (sex) {
            case 0:
                sexString = "男";
                break;
            case 1:
                sexString = "女";
                break;
            default:
                break;
        }
        return sexString;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey   = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }
}
