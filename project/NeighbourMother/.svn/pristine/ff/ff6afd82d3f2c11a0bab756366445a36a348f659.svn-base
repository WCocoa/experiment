package com.qiantang.neighbourmother.ui.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.ImgcDetailAdapter;
import com.qiantang.neighbourmother.adapter.ImgcImgListAdapter;
import com.qiantang.neighbourmother.model.ImgcImageFloder;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.ImgMCFinal;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;

public class ImgcImgListActivity extends BaseActivity implements OnClickListener {

    private ListView imglist;
    private ImageView imglist_cancer;
    private ArrayList<ImgcImageFloder> mImageFloders = new ArrayList<ImgcImageFloder>();
    private ProgressDialog mProgressDialog;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    private int totalCount = 0;

    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;

    private ImgcImgListAdapter imglistAdapter;

    private ArrayList<String> select_img = new ArrayList<String>();

    @Override
    public int getContentView() {
        // TODO Auto-generated method stub
        return R.layout.activity_imgc_imglist;
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        imglist = (ListView) findViewById(R.id.imglist);
        imglist_cancer = (ImageView) findViewById(R.id.imglist_cancer);
        select_img.clear();
        if (ImgcDetailAdapter.mSelectedImage != null)
            ImgcDetailAdapter.mSelectedImage.clear();
//		initHandler();
        getImages();
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
    }

    @Override
    public void initEvent() {
        // TODO Auto-generated method stub

        imglist_cancer.setOnClickListener(this);
        imglist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(ImgcImgListActivity.this,
                        ImgcImgDetailActivity.class);
                intent.putExtra(ImgMCFinal.INTNET_IMG_FOLDER,
                        mImageFloders.get(arg2));
                intent.putStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE,
                        select_img);
                startActivityForResult(intent, 2);
            }
        });

    }


    @Override
    protected void updateUI(Message msg) {
        // TODO Auto-generated method stub
        switch (msg.what) {
            case 1:
                imglistAdapter = new ImgcImgListAdapter(ImgcImgListActivity.this,
                        mImageFloders);
                imglist.setAdapter(imglistAdapter);
//			setEvent();
                mProgressDialog.cancel();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.imglist_cancer) {
            finish();
        }
    }


//	private void setEvent() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                // 按了返回
                if (data != null) {
                    select_img = data
                            .getStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE);
                    System.out.println("select_img:" + select_img.size());
                }
                break;
            case 2:
                finish();
                break;
            case 3:
                Intent intent = new Intent();
                intent.putStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE,
                        data.getStringArrayListExtra(ImgMCFinal.INTNET_IMG_CHOOSE));
                System.out.println("select_img:" + select_img.size());
                setResult(ImgMCFinal.REQUEST_CODE_GETIMAGE_BYSDCARD, intent);
                finish();
                break;
        }
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ImgcImgListActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                Log.e("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    Log.e("TAG", path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImgcImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImgcImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

}