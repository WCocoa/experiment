package com.qiantang.neighbourmother.logic;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:输入金额过滤器
 * author: Cocoa
 * date: 2016/8/15.
 */
public class MoneyInputFlter implements InputFilter {
    Pattern mPattern;
    private static final double MAX_VALUE      = 999999999.99;
    private static final int    POINTER_LENGTH = 2;
    private static final String POINTER        = ".";
    private static final String ZERO           = "0";

    public MoneyInputFlter() {
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
       /* String sourceText = source.toString();
        String destText = dest.toString();
        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        Matcher matcher = mPattern.matcher(source);
        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.equals(source)) {  //只能输入一个小数点
                    return "";
                }
            }

            //验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(POINTER);
            int length = dend - index;

            if (length > POINTER_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!matcher.matches()) {
                return "";
            } else {
                if ((POINTER.equals(source) || ZERO.equals(source)) && TextUtils.isEmpty(destText)) {
                    return "";
                }
            }
        }
        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText > MAX_VALUE) {
            return dest.subSequence(dstart, dend);
        }
//        if ("".equals(source.toString())) {
//            return null;
//        }
//
//        String dValue = dest.toString();
//        String[] splitArray = dValue.split(".");
//        if (splitArray.length > 1) {
//            String dotValue = splitArray[1];
//            int diff = dotValue.length() + 1 - 1;
//            if (diff > 0) {
//                return source.subSequence(start, end - diff);
//            }
//        }
            return dest.subSequence(dstart, dend) + sourceText;*/
        Log.i(this.getClass().toString(), source + "--" + String.valueOf(start) + "--" + String.valueOf(end) + "--" + "--" + String.valueOf(dest) + "--" + String.valueOf(dstart) + "--" + String.valueOf(dend) + "--");

        String so = source.toString();
        String de = dest.toString();
        if ("".equals(so) || end == 0) {
            de = de.substring(0, dstart) + de.substring(dend, de.length());
        } else {
            de = de.substring(0, dstart) + so + de.substring(dend, de.length());
        }
        System.out.println(de);

        Pattern pattern = Pattern.compile("^\\d{0,9}\\.?(?:\\.\\d{0,2})?$");
        Matcher matcher = pattern.matcher(de);
        if (matcher.find()) {
            System.out.println("a" + de);
            return null;
        } else {
            System.out.println("b" + dest.toString());
            return "";
        }

    }
}
