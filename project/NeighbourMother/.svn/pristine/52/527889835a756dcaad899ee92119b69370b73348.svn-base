package com.qiantang.neighbourmother.util;

import android.content.Context;
import android.os.Environment;

import com.qiantang.neighbourmother.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class QLFileUtil {
    // 文件夹拷贝
    public int copyFolder(String fromFile, String toFile) {
        // 要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
        // 如同判断SD卡是否存在或者文件是否存在
        // 如果不存在则 return出去
        if (!root.exists()) {
            return -1;
        }
        // 如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();

        // 目标目录
        File targetDir = new File(toFile);
        // 创建目录
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        // 遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())// 如果当前项为子目录 进行递归
            {
                copyFolder(currentFiles[i].getPath() + "/",
                        toFile + currentFiles[i].getName() + "/");

            } else// 如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile
                        + currentFiles[i].getName());
            }
        }
        return 0;
    }

    // 文件拷贝
    // 要复制的目录下的所有非子目录(文件夹)文件拷贝
    public int CopySdcardFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    // 比较两个目录下文件是否一致（文件夹个数、文件个数、文件大小）
    // （返回0表示一致，返回-1表示不一致）
    public int CheckFile(String fromFilePath, String toFilePath) {

        FilesInfo fromFile = new FilesInfo(fromFilePath); // 获取路径1下文件的信息

        FilesInfo toFile = new FilesInfo(toFilePath);// 获取路径2下文件的信息

        if (fromFile.getCheck() == -1 || fromFile.getCheck() == -1) { // 如果其中一个路径不存在
            return -1;
        } else if (fromFile.getDirectoryAmounts() != toFile
                .getDirectoryAmounts()) {// 如果两个路径下文件夹个数不一致
            return -1;
        } else if (fromFile.getFileAmounts() != toFile.getFileAmounts()) {// 如果两个路径下文件个数不一致
            return -1;
        } else { // 比较两个路径下文件的大小
            List<File> fromFileList = fromFile.getFileList(); // 获取路径1下文件List
            List<File> toFileList = toFile.getFileList(); // 获取路径2下文件List

            if (fromFileList.size() == 0 || toFileList.size() == 0) { // 如果其中一个路径下文件List大小为0返回-1
                return -1;
            }

            Iterator<File> fromFileIterator = fromFileList.iterator();

            while (fromFileIterator.hasNext()) { // 遍历fromFile

                File tempFromFile = (File) fromFileIterator.next();
                String tempFromFileName = tempFromFile.getName();
                long tempFromFileLength = tempFromFile.length();

                boolean Same = false;

                Iterator<File> toFileIterator = toFileList.iterator();
                while (toFileIterator.hasNext()) { // 遍历toFile
                    File tempToFile = (File) toFileIterator.next();
                    if (tempToFile.getName().equals(tempFromFileName)) {

                        if (tempToFile.length() == tempFromFileLength) { // 长度一致
                            Same = true;
                            break;
                        }
                        break; // 找到但长度不一致
                    }
                }
                if (!Same) {
                    return -1;
                }
            }
            return 0;
        }
    }

    //因为COPY文件到    SDCARD 需要权限，因此需要在应用程序的AndroidManifest.xml加上权限，如下：
    //<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    public void copyFileSystemToMnt() {
        Process process = null;
        try {
            //process = Runtime.getRuntime().exec("getprop ro.board.platform");
            //busybox cp
            process = Runtime.getRuntime().exec("busybox cp /system/manual /mnt/sdcard/ -rf");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public class FilesInfo {

        private final String SDCard = "/mnt/sdcard/external_sd";

        private int directoryAmounts;

        private int fileAmounts;

        private int check;

        private List<File> fileList;

        public FilesInfo(String filePath) {
            directoryAmounts = 0;

            fileAmounts = 0;

            fileList = new ArrayList<File>();

            check = init(filePath);
        }

        public int init(String filePath) {
            File[] currentFiles;
            File root = new File(filePath);
            if (!root.exists()) {
                return -1;
            }
            currentFiles = root.listFiles();

            for (int i = 0; i < currentFiles.length; i++) {
                if (currentFiles[i].isDirectory()) {
                    if (currentFiles[i].getAbsolutePath().equals(SDCard)) {
                        continue;
                    }
                    directoryAmounts++;
                    init(currentFiles[i].getAbsolutePath());
                    continue;
                }
                fileAmounts++;
                fileList.add(currentFiles[i]);
            }
            return 0;
        }

        public int getDirectoryAmounts() {
            return directoryAmounts;
        }

        public int getFileAmounts() {
            return fileAmounts;
        }

        public int getCheck() {
            return check;
        }

        public List<File> getFileList() {
            return fileList;
        }

    }


    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return String
     */
    public static String getFileName(String filePath) {
        if (QLStringUtil.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    public void writerFileToApp(Context context, String fileName) {
        writerFileToApp(context, fileName, Context.MODE_PRIVATE);
    }

    public void writerFileToApp(Context context, String fileName, int mode) {

        OutputStream out = null;
        try {
            out = context.openFileOutput(fileName, mode);
            out.write(fileName.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String readFileFromApp(Context context, String fileName) {
        String result = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = context.openFileInput(fileName);
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            result = outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        return result;
    }

    public void writerFileToSDcard(Context context, String fileName, String content) {
        OutputStream out = null;
        // 判断是否sdCard是否可用
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                out = new FileOutputStream(new File(fileName));
                out.write(content.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            ToastUtils.toastshort(context, context.getResources().getString(R.string.lpfileutil_unidentified_sdcard));
        }
    }

    public String readFileToSDcard(Context context, String fileName) {
        BufferedReader reader = null;
        String str = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                reader = new BufferedReader(new FileReader(new File(fileName)));
                StringBuilder sb = new StringBuilder("");


                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        } else {
            ToastUtils.toastshort(context, context.getResources().getString(R.string.lpfileutil_unidentified_sdcard));
        }
        return str.toString();
    }


    public static void createDirs(String dirs) {
        String str[] = dirs.split(File.separator);
        if (str != null && str.length > 1) {
            for (int i = 0; i < str.length; i++) {

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= i; j++) {
                    sb.append(str[i]);
                    sb.append(File.separator);
                }
                createDir(sb.toString());
            }
        }
    }

    public static void createDir(String dirs) {
        File destDir = new File(dirs);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

}
