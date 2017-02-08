package com.qiantang.neighbourmother.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.AdObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.List;

/**
 * 咨询介绍广告轮播
 *
 * @author zb
 * @date 创建时间：2015年9月22日 下午4:46:27
 */
public class HomeAdPagerAdapter extends PagerAdapter {

    private List<AdObj> adList = null;
    private Context context;

    private int defaultImg;

    public HomeAdPagerAdapter(Context context, List<AdObj> adList) {
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
        final int index = position % adList.size();
        View view = View
                .inflate(context, R.layout.item_ad, null);
        ImageView ad_imageview = (ImageView) view
                .findViewById(R.id.ad_imageview);

//                AppLog.D("API.BASE_IMG_URL + adList.get(index).getAd_image():"+API.BASE_IMG_URL + adList.get(index).getAd_image());
        ((BaseActivity) context).display(ad_imageview, APIConfig.BASE_IMG_URL + adList.get(index).getAd_image(), defaultImg);

//        ad_imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = null;
//                clickImage(index,intent);

//            }
//        });
        ((ViewPager) container).addView(view);
        return view;

    }

//    private void clickImage(int index,Intent intent){
//        switch (adList.get(index).getAd_type()) {
//            case 1:
//                InstitutionObj institutionObj = new InstitutionObj();
//                institutionObj.setUser_id(adList.get(index).ad_id);
//                intent = new Intent(context, InstitutionDetailActivity.class);
//                intent.putExtra(IntentFinal.INTENT_INSTITUTION_OBJ_KEY, institutionObj);
//                context.startActivity(intent);
////                        ToastUtils.showToast(context, "1.机构详情:" + adList.get(index).getAd_id());
//                break;
//            case 2:
//                CurriculumObj curriculumObj = new CurriculumObj();
//                curriculumObj.set_id(adList.get(index).getAd_id());
//                intent = new Intent(context,
//                        CurriculumDetailActivity.class);
//                intent.putExtra(IntentFinal.INTENT_CURRICULUM_OBJ,
//                        curriculumObj);
//                context.startActivity(intent);
//                        ToastUtils.showToast(context, "2.课程详情:" + adList.get(index).getAd_id());
//                break;
//            default:
//                break;
//        }
//    }

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
