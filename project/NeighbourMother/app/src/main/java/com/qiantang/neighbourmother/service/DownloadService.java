package com.qiantang.neighbourmother.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.VersionResp;
import com.qiantang.neighbourmother.ui.MainTabActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.FileFinal;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载安装服务
 */
@SuppressWarnings("all")
public class DownloadService extends Service {

    private static NotificationManager nm;
//    private static Notification notification;
private Notification.Builder builder;
    private static boolean cancelUpdate = false;
    private static MyHandler myHandler;
    private static ExecutorService executorService = Executors
            .newFixedThreadPool(5); // 固定五个线程来执行任
    public static Map<Integer, Integer> download = new HashMap<Integer, Integer>();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    private VersionResp versionResp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        AppLog.D("DownloadService.onStartCommand");
        if (intent != null) {
            versionResp = (VersionResp) intent.getSerializableExtra(IntentFinal.INTENT_VERSION_OBJ_KEY);
            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            myHandler = new MyHandler(Looper.myLooper(), DownloadService.this);
            new Thread() {
                @Override
                public void run() {
                    if (versionResp != null) {
                        downUpdateApk(versionResp.getSoft_address());
                    }
                }
            }.start();

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private synchronized void downUpdateApk(String newVersionUrl) {
//        AppLog.D("DownloadService.downUpdateApk");
        File folder = new File(FileFinal.PATH_APK_DIR);
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (newVersionUrl != null || newVersionUrl.length() > 0) {
            downNewFile(newVersionUrl, 351, getString(R.string.app_name), FileFinal.PATH_APK_DIR);
        }
    }

    private void downNewFile(final String url, final int notificationId,
                             final String name, final String saveFloder) {
        if (download.containsKey(notificationId))
            return;

        notificBuild1(name,notificationId);

        // 启动线程始执行下载任
        downFile1(url, notificationId, name, saveFloder);
    }

    private void notificBuild1(String name, int notificationId){

         builder = new Notification.Builder(this);
        builder.setAutoCancel(true);
        builder.setSmallIcon(android.R.drawable.stat_sys_download);
        builder.setTicker(name + "开始下载");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        builder.setOngoing(true);
                PendingIntent contentIntent = PendingIntent.getActivity(this,
                notificationId, new Intent(this, MainTabActivity.class), 0);

        updateNotific(name,"0%",contentIntent,notificationId);

        // 将下载任务添加到任务栏中
//        nm.notify(notificationId, builder.build());
        download.put(notificationId, 0);

    }


    private void updateNotific(String title, String text, PendingIntent intent, int notificaId){
        if (title != null) {
            builder.setContentTitle(title);
        }
        if (text != null) {
        builder.setContentText(text);
        }
        builder.setContentIntent(intent);
        nm.notify(notificaId, builder.build());
    }

    // 安装下载后的apk文件
    private void Instanll(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /* 事件处理 */
    class MyHandler extends Handler {
        private Context context;

        public MyHandler(Looper looper, Context c) {
            super(looper);
            this.context = c;
        }

        @Override
        public void handleMessage(Message msg) {
            PendingIntent contentIntent = null;
            super.handleMessage(msg);
            if (msg != null) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(context, msg.obj.toString(),
                                Toast.LENGTH_SHORT).show();
                        download.remove(msg.arg1);
                        break;
                    case 1:
                        break;
                    case 2:
                        contentIntent = PendingIntent.getActivity(
                                DownloadService.this, msg.arg1, new Intent(
                                        DownloadService.this, MainTabActivity.class),
                                0);
//                        notification.setLatestEventInfo(DownloadService.this, msg
//                                        .getData().getString("name") + "下载完成", "100%",
//                                contentIntent);
//
//
//
//                        nm.notify(msg.arg1, notification);

                        updateNotific(msg
                                .getData().getString("name") + "下载完成","100%",contentIntent,msg.arg1);

                        // 下载完成后清除所有下载信息，执行安装提示
                        download.remove(msg.arg1);
                        nm.cancel(msg.arg1);
                        Instanll((File) msg.obj, context);
                        break;
                    case 3:
                        contentIntent = PendingIntent.getActivity(
                                DownloadService.this, msg.arg1, new Intent(
                                        DownloadService.this, MainTabActivity.class),
                                0);

                        updateNotific(msg
                                .getData().getString("name") + "正在下载","当前下载进度"
                                + download.get(msg.arg1) + "%",contentIntent,msg.arg1);

                        break;
                    case 4:
                        Toast.makeText(context, msg.obj.toString(),
                                Toast.LENGTH_SHORT).show();
                        download.remove(msg.arg1);
                        nm.cancel(msg.arg1);
                        break;
                }
            }
        }
    }

    // 下载更新文件
    private void downFile1(final String url, final int notificationId,
                           final String name, final String savePath) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                File tempFile = null;

                try {
                    URL url1=new URL(url);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();

                    long length =httpURLConnection.getContentLength();
                    InputStream is=httpURLConnection.getInputStream();

                                        if (is != null) {
                        tempFile = new File(savePath + name + ".apk");
                        ////////System.out.println(tempFile.getPath());
                        if (tempFile.exists())
                            tempFile.delete();
                        tempFile.createNewFile();
                        ////////System.out.println(tempFile.getPath());

                        // 已读出流作为参数创建
                        BufferedInputStream bis = new BufferedInputStream(is);
                        // 创建新的写入流，讲读取到的图像数据写入到文件中
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        // 已写入流作为参数创建个带有缓冲的写入
                        BufferedOutputStream bos = new BufferedOutputStream(fos);

                        int read;
                        long count = 0;
                        int precent = 0;
                        byte[] buffer = new byte[1024];
                        while ((read = bis.read(buffer)) != -1 && !cancelUpdate) {
                            bos.write(buffer, 0, read);
                            count += read;
                            precent = (int) (((double) count / length) * 100);

                            // 下载进度
                            if (precent - download.get(notificationId) >= 1) {
                                download.put(notificationId, precent);
                                Message message = myHandler.obtainMessage(3,
                                        precent);
                                Bundle bundle = new Bundle();
                                bundle.putString("name", name);
                                message.setData(bundle);
                                message.arg1 = notificationId;
                                myHandler.sendMessage(message);
                            }
                        }
                        bos.flush();
                        bos.close();
                        fos.flush();
                        fos.close();
                        is.close();
                        bis.close();
                    }

                                        if (!cancelUpdate) {
                        Message message = myHandler.obtainMessage(2, tempFile);
                        message.arg1 = notificationId;
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        message.setData(bundle);
                        myHandler.sendMessage(message);
                    } else {
                        if (tempFile != null) {
                            tempFile.delete();
                        }

                    }

                } catch (MalformedURLException e) {
                    if (tempFile.exists())
                        tempFile.delete();
                    Message message = myHandler.obtainMessage(4, name + "下载失败：网络异常！");
                    message.arg1 = notificationId;
                    myHandler.sendMessage(message);
//                    e.printStackTrace();
                }catch (IOException e) {
//                    e.printStackTrace();
                                        if (tempFile.exists())
                        tempFile.delete();
                    Message message = myHandler.obtainMessage(4, name + "下载失败：文件传输异常");
                    message.arg1 = notificationId;
                    myHandler.sendMessage(message);
                }

            }
        });
    }

}
