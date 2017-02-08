package com.qiantang.neighbourmother.util;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;


public class SpannableUtils {
    private SpannableUtils( ){
         
    }
     
    /**
     * 设置字体
     * @param content
     * @param startIndex
     * @param endIndex
     * @param fontSize
     * @return
     */
    public static SpannableString setTextSize( String content, int startIndex, int endIndex, int fontSize ){
        if( TextUtils.isEmpty( content ) || fontSize <= 0 || startIndex >= endIndex || startIndex < 0 || endIndex >= content.length( ) ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new AbsoluteSizeSpan( fontSize ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
         
        return spannableString;
    }
     /**
      * 设置下标
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextSub( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new SubscriptSpan( ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
         
        return spannableString;
    }
     /**
      * 设置上标
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextSuper( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new SuperscriptSpan( ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
         
        return spannableString;
    }
     /**
      * 中间删除线
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextStrikethrough( String content, int startIndex, int endIndex ){

        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex > content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StrikethroughSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 下划线
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextUnderline( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 加粗
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextBold( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 加斜
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextItalic( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 加粗加斜
      * @param content
      * @param startIndex
      * @param endIndex
      * @return
      */
    public static SpannableString setTextBoldItalic( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 设置前景色
      * @param content
      * @param startIndex
      * @param endIndex
      * @param foregroundColor
      * @return
      */
    public static SpannableString setTextForeground( String content, int startIndex, int endIndex, int foregroundColor ){
//        AppLog.D("content:"+content);
//        AppLog.D("content:"+content.length());
//        AppLog.D("startIndex:"+startIndex);
//        AppLog.D("endIndex:"+endIndex);
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex > content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new ForegroundColorSpan( foregroundColor ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 设置背景色
      * @param content
      * @param startIndex
      * @param endIndex
      * @param backgroundColor
      * @return
      */
    public static SpannableString setTextBackground( String content, int startIndex, int endIndex, int backgroundColor ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new BackgroundColorSpan( backgroundColor ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     
    /**
     * 设置超链接
     * @param content
     * @param startIndex
     * @param endIndex
     * @param url
     * @return
     */
    public static SpannableString setTextURL( String content, int startIndex, int endIndex, String url ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new URLSpan( url ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
     /**
      * 设置图片
      * @param content
      * @param startIndex
      * @param endIndex
      * @param drawable
      * @return
      */
    public static SpannableString setTextImg( String content, int startIndex, int endIndex, Drawable drawable ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }
         
        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new ImageSpan(drawable), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         
        return spannableString;
    }
}