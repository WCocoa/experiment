package com.qiantang.neighbourmother.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.PropertyConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Created by Administrator on 2016/3/16.
 */
public class DBManager {

    private final int BUFFER_SIZE = 400000;
    public  String PACKAGE_NAME;
    public  String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/";  //在手机里存放数据库的位置

    private SQLiteDatabase database;
    private Context context;
    private PropertyConfig propertyConfig;
    private int perVersion;

    public DBManager(Context context) {
        this.context = context;
        propertyConfig = PropertyConfig.getInstance(context);
        PACKAGE_NAME = context.getPackageName();
        DB_PATH=DB_PATH+PACKAGE_NAME;
        AppLog.D("DB_PATH:"+DB_PATH);
    }

    public SQLiteDatabase openDatabase() {
        perVersion = propertyConfig.getInt(IntentFinal.INTENT_VERSION_OF_DB, 0);

        this.database = this.openDatabase(DB_PATH + "/" + CitySql.DB_NAME);
        return database;
    }

//    public SQLiteDatabase openDatabase1() {
//        perVersion = propertyConfig.getInt(IntentFinal.INTENT_VERSION_OF_DB, 0);
//        PACKAGE_NAME = context.getPackageName();
//        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME1);
//        return database;
//    }

    private SQLiteDatabase openDatabase(String dbfile) {
        File file = new File(dbfile);
        if (!(file.exists())) {
            importDB(dbfile);
            propertyConfig.save(IntentFinal.INTENT_VERSION_OF_DB, NeighbourMotherApplication.versionCode);
        } else if (NeighbourMotherApplication.versionCode > perVersion) {
            file.delete();
            importDB(dbfile);
            propertyConfig.save(IntentFinal.INTENT_VERSION_OF_DB, NeighbourMotherApplication.versionCode);
        }

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                null);
        db.setLocale(Locale.CHINA);
//        SQLiteDatabase db=SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null,SQLiteDatabase.NO_LOCALIZED_COLLATORS|SQLiteDatabase.CREATE_IF_NECESSARY);
        return db;
    }





    private void importDB(String dbfile) {
        //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
        try {
            InputStream is = this.context.getResources().openRawResource(
                    R.raw.zhifu); //欲导入的数据库
            FileOutputStream fos = new FileOutputStream(dbfile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();
        } catch (IOException e1) {
            Log.e("Database", "IO exception");
            e1.printStackTrace();
        }
    }
}
