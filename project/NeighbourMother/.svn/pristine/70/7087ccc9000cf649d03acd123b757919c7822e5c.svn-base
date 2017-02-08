package com.qiantang.neighbourmother.util;

import android.os.Environment;

import java.io.File;


/**
 * @author quliang
 * @version 2015-8-31 下午4:06:38
 *          desc 文件常量
 */
public class FileFinal {

    /**
     * 作用: SD卡根目录
     */
    public static final String PATH = Environment.getExternalStorageDirectory()
            .getPath();

    /**
     * 作用: 数据文件夹
     */
    public static final String PATH_ROOT = PATH + "/neighbourMather/";
    /**
     * 作用: 异步加载网络图片缓存文件夹
     */
    public final static String PATH_CACHEIMG = PATH_ROOT + "cacheImg/";
    /**
     * 作用: 下载apk目录
     */
    public final static String PATH_APK_DIR = FileFinal.PATH_ROOT + "APK/";
    /**
     * 图像临时名称 ,修改完图像立即删除
     */
    public final static String PATH_TEMP = PATH_ROOT + "temp/";
    /**
     * 作用: 用户头像名称
     */
    public final static String USER_IMG_HAED = "user_imghead.jpg";
    /**
     * 作用: 分享app_icon文件
     */
    public final static String SHARE_APP_ICON = "share_app_icon1.jpg";

    public void clearCache() {
        File rootFile = new File(PATH_ROOT);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (rootFile.exists()) {
                deleteFile();
            }
        }
    }

    private void deleteFile() {
        deleteFiles(PATH_CACHEIMG);
        deleteFiles(PATH_TEMP);
        deleteFiles(PATH_APK_DIR);
    }

    /**
     * @param filepath
     */
    private void deleteFiles(String filepath) {
        File f = new File(filepath);
        if (f.exists()) {
            File delFile[] = f.listFiles();
            for (File file : delFile) {
                AppLog.D("清理文件夹了没有file:" + file.getAbsolutePath());
                file.delete();
            }
        }
    }

    public static void createAppDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            QLFileUtil.createDir(PATH_ROOT);
            QLFileUtil.createDir(PATH_CACHEIMG);
            QLFileUtil.createDir(PATH_TEMP);
            QLFileUtil.createDir(PATH_APK_DIR);
        }
    }

}
