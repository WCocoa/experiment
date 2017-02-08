package com.qiantang.neighbourmother.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.PostObj;
import com.qiantang.neighbourmother.model.SliderObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.community.PosterDetailActivity;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.util.List;


public class ConmmunityAdPagerAdapter extends PagerAdapter {

    private List<SliderObj> adList = null;
    private Context context;

    private int defaultImg;

    public ConmmunityAdPagerAdapter(Context context, List<SliderObj> adList) {
        this.context = context;

        this.adList = adList;
//        defaultImg = R.drawable.subject_default_icon;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        View view = View
                .inflate(context, R.layout.item_ad, null);
        ImageView ad_imageview = (ImageView) view
                .findViewById(R.id.ad_imageview);
        if (adList.size() > 0) {
            final int index = position % adList.size();
//                AppLog.D("API.BASE_IMG_URL + adList.get(index).getAd_image():"+API.BASE_IMG_URL + adList.get(index).getAd_image());
            ((BaseActivity) context).display(ad_imageview, APIConfig.BASE_IMG_URL + adList.get(index).getPost_pic(), R.mipmap.default_img);
//        ImageLoader.getInstance().displayImage("drawable://" + adList.get(index).getPost_pic(), ad_imageview);
            ad_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    clickImage(index);
                }
            });

        } else {
            ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.default_img, ad_imageview);
        }
        ((ViewPager) container).addView(view);
        return view;

    }

    private void clickImage(int index) {
//        ToastUtils.toastLong(context, "index" + adList.get(index));
        PostObj postObj = new PostObj();
        postObj.setPost_id(adList.get(index).getPost_id());
        Intent intent = new Intent(context, PosterDetailActivity.class);
        intent.putExtra(IntentFinal.INTENT_POSTER_DETAIL_OBJ, postObj);
        context.startActivity(intent);

//        switch (index) {
//            case 1:
//
//
//                break;
//            case 2:
//
//                ToastUtils.toastLong(context, "2.课程详情:" + adList.get(index).getAd_id());
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // if(position==0&&adList.length>1)
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

}
