package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.logic.TakeImage;
import com.qiantang.neighbourmother.model.SposterObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

/**
 * ClassName:订单列表适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class SendPosterAdapter extends BaseAdapter {
    private ArrayList<SposterObj> list = new ArrayList<SposterObj>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;
    private Handler       handler;
    private TakeImage takeImage;
    private int currentIndex;

    private MyTextWatcher myTextWatcher_title;
    private MyTextWatcher myTextWatcher_content;
    //定义成员变量mTouchItemPosition,用来记录手指触摸的EditText的位置
//    private int mTouchItemPosition_title = -1;
    private int mTouchItemPosition_content = -1;
    private boolean istitlte;

    public SendPosterAdapter(Activity context,Handler handler) {
        this.context = context;
        this.handler = handler;
        mLayoutInflater = LayoutInflater.from(context);
        takeImage = new TakeImage(context, handler, false);
        myTextWatcher_title=new MyTextWatcher();
        myTextWatcher_content=new MyTextWatcher();
        takeImage.setOnPictureListener(new TakeImage.PictureListener() {
            @Override
            public void pictureCall(final String path) {
                AppLog.D("setOnPictureListener:" + path);
                if (!TextUtils.isEmpty(path)) {
                   list.get(currentIndex).setLocImgPath(path);
                    SendPosterAdapter.this.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    public ArrayList<SposterObj> getDataList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (getCount() <= 0 || position >= getCount())
            return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_sendposter, null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.delete = (ImageView) convertView.findViewById(R.id.delete);
            holder.title = (EditText) convertView.findViewById(R.id.title);
            holder.content = (EditText) convertView.findViewById(R.id.content);
            holder.delete_item = (TextView) convertView.findViewById(R.id.delete_item);



            holder.title.addTextChangedListener(myTextWatcher_title);
            myTextWatcher_title.update(0,position);
            holder.content.addTextChangedListener(myTextWatcher_content);
            myTextWatcher_content.update(1,position);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            myTextWatcher_content.update(1,position);
            myTextWatcher_title.update(0,position);
        }

       final SposterObj sposterObj=list.get(position);

        if(TextUtils.isEmpty(sposterObj.getLocImgPath())){
            holder.delete.setVisibility(View.GONE);
        }else{
            if(position==0){

                holder.delete.setVisibility(View.GONE);
            }else{
                holder.delete.setVisibility(View.VISIBLE);
            }
        }

        holder.delete_item.setVisibility(position==0?View.GONE:View.VISIBLE);
        holder.title.setVisibility(position==0?View.VISIBLE:View.GONE);
        holder.title.setTag(position);
        holder.content.setTag(position);

//        if(mTouchItemPosition_content == position){
//            if(istitlte){
//                holder.title.requestFocus();
//                holder.title.setSelection(holder.title.getText().length());
//
//            }else{
//                holder.content.requestFocus();
//                holder.content.setSelection(holder.content.getText().length());
//            }
//        }

        if ((mTouchItemPosition_content == position&&istitlte)) {
            holder.title.requestFocus();
            holder.title.setSelection(holder.title.getText().length());
            holder.content.clearFocus();
        } else if ((mTouchItemPosition_content == position&&!istitlte)){
            holder.content.requestFocus();
            holder.content.setSelection(holder.content.getText().length());
            holder.title.clearFocus();

        }else {
//            holder.title.clearFocus();
//            holder.content.clearFocus();
        }


        if (mTouchItemPosition_content == position) {
            holder.content.requestFocus();
            holder.content.setSelection(holder.content.getText().length());
        } else {
            holder.content.clearFocus();
        }

        holder.title.setText(sposterObj.getTitle());
        holder.content.setText(sposterObj.getContent());

        handler.post(new MyRunnable(holder.image,sposterObj.getLocImgPath()));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex=position;
                takeImage.showDialog();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sposterObj.setLocImgPath("");
                notifyDataSetChanged();
            }
        });

        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });




        holder.content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                mTouchItemPosition_content = (Integer) view.getTag();
                istitlte=true;
//                return false;

//                触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if ((view.getId() == R.id.content && canVerticalScroll((EditText)view))) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }

        });

        holder.title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                mTouchItemPosition_content = (Integer) view.getTag();
                istitlte=false;
//                return false;

                if ((view.getId() == R.id.title && canVerticalScroll((EditText)view))) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        return convertView;
    }

    /**
     * EditText竖直方向是否可以滚动
     * @param editText 需要判断的EditText
     * @return true：可以滚动 false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    class ViewHolder {
        ImageView  image;
        ImageView  delete;
        EditText   title;
        EditText   content;
        TextView   delete_item;
    }

    class  MyRunnable implements Runnable{
        ImageView imageView;
        String imgPath;
        MyRunnable(ImageView imageView,String imgPath){
            this.imageView=imageView;
            this.imgPath=imgPath;
        }
        @Override
        public void run() {
            ImageLoader.getInstance().displayImage(API.locImgPathPrefix + imgPath, imageView, ((BaseActivity) context).getDisplayImageOptions(R.mipmap.icon_soc_close));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (takeImage != null)
            takeImage.onActivityResult(requestCode, resultCode, data);
    }


    public class MyTextWatcher implements TextWatcher{
//        0title 1summary
private int type;
        private int pos;
//        public MyTextWatcher(int type,int pos){
//            this.type=type;
//            this.pos=pos;
//        }

        public void update(int type,int pos){
            this.type=type;
            this.pos=pos;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            SposterObj sposterObj= list.get(pos);
            switch (type){
                case 0:
                    sposterObj.setTitle(s.toString());
                    break;
                case 1:
                    sposterObj.setContent(s.toString());
                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
