-optimizationpasses 5  
-dontusemixedcaseclassnames  
-dontskipnonpubliclibraryclasses  
-dontpreverify
-dontwarn com.baidu.**
-verbose  
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  
  
-keepattributes *Annotation*  
-keepattributes Signature  

#-libraryjars libs/gson-2.2.2.jar
#-libraryjars libs/locSDK_6.03.jar
#-libraryjars libs/pinyin4j-2.5.0.jar
#-libraryjars libs/pushservice-4.5.3.48.jar
#-libraryjars libs/universal-image-loader-1.9.4.jar
#-libraryjars libs/fastjson-1.2.4.jar
#-libraryjars libs/alipaySdk-20160111.jar
#-libraryjars libs/commons-lang-2.6.jar
#-libraryjars libs/libammsdk.jar
#-libraryjars libs/mta-sdk-1.6.2.jar
#-libraryjars libs/open_sdk_r5043.jar
#-libraryjars libs/weibosdkcore.jar
#-libraryjars libs/pushservice-VERSION.jar
#-libraryjars libs/alipaySDK-20150602.jar

-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.baidu.** { *; }   
-keep public class * extends android.app.Fragment    
-keep public class * extends android.app.Activity  
-keep public class * extends android.app.Application  
-keep public class * extends android.app.Service  
-keep public class * extends android.content.BroadcastReceiver  
-keep public class * extends android.content.ContentProvider  
-keep public class * extends android.app.backup.BackupAgentHelper  
-keep public class * extends android.preference.Preference  
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep class com.google.** { *; }
-keep class com.baidu.location.** { *; }
-keep class net.sourceforge.pinyin4j.**{*;}
-keep class net.sourceforge.pinyin4j.format.**{*;}
-keep class net.sourceforge.pinyin4j.format.exception.**{*;}
#-keep class com.alibaba.fastjson.** {*;}
-keep class com.qiantang.neighbourmother.business.response.** {*;}
-keep class com.qiantang.neighbourmother.business.request.** {*;}
-keep class com.qiantang.neighbourmother.business.data.** {*;}
-keep class com.qiantang.neighbourmother.model.** {*;}
-keep class com.tencent.** {*;}
#-keep class vi.com.** {*;}
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

-dontwarn **
-dontwarn net.sourceforge.pinyin4j.**  
-dontwarn android.support.**
-dontwarn com.baidu.location.**
-dontwarn com.tencent.**
-dontwarn com.alibaba.fastjson.**
-dontwarn com.qiantang.neighbourmother.business.response.**
-dontwarn com.qiantang.neighbourmother.business.request.**
-dontwarn com.qiantang.neighbourmother.model.**




-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}



-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclasseswithmembers class * {  
    public <init>(android.content.Context);  
} 

-keepclassmembers class **.R$* {
    public static <fields>;
}

#-keepnames class * implements java.io.Serializable

-keep public class * implements java.io.Serializable {
        public *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class com.qiantang.neighbourmother.R$*{
public static final int *;
}

-keepclassmembers class * {
    public <init> (org.json.JSONObject);
    public <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
    public <fields>;
}





-dontshrink  
-dontoptimize

-dontwarn android.support.v4.**


# adding this in to preserve line numbers so that the stack traces
# can be remapped
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable