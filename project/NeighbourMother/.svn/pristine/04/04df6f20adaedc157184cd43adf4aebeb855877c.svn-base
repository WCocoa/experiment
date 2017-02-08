package com.qiantang.neighbourmother.logic;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.QLScreenUtil;
import com.qiantang.neighbourmother.widget.taglayout.Tag;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/12/12.
 */
public class BbsTagUtil {
    private List<Tag> mTags = new ArrayList<Tag>();

    public Tag getTag(Context context, int id) {

        return getTagTextString(context, id);
    }

    public Tag getTag(Context context, String id) {
        int newId = Integer.valueOf(id);

        return getTagTextString(context, newId);
    }

    public List<Tag> getmTags() {
        return mTags;
    }

    /**
     * 根据标签ID获取标签对象
     *
     * @param context
     * @param id
     * @return
     */
    private Tag getTagTextString(Context context, int id) {
//        initTagList(context);
        //        if (id <= mTags.size())
//            tag = mTags.get(id - 1);

        Tag tag = null;

        switch (id) {
            case 1:
                tag = new Tag(1, false, context.getString(R.string.bbstag_tag1), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_1));
                break;
            case 2:
                tag = new Tag(2, false, context.getString(R.string.bbstag_tag2), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_2));
                break;
            case 3:
                tag = new Tag(3, false, context.getString(R.string.bbstag_tag3), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_3));
                break;
            case 4:
                tag = new Tag(4, false, context.getString(R.string.bbstag_tag4), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_4));
                break;
            case 5:
                tag = new Tag(5, false, context.getString(R.string.bbstag_tag5), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_5));
                break;
            case 6:
                tag = new Tag(6, false, context.getString(R.string.bbstag_tag6), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_6));
                break;
            case 7:
                tag = new Tag(7, false, context.getString(R.string.bbstag_tag7), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_7));
                break;
            case 8:
                tag = new Tag(8, false, context.getString(R.string.bbstag_tag8), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_8));
                break;
            case 9:
                tag = new Tag(9, false, context.getString(R.string.bbstag_tag9), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_9));
                break;
            case 10:
                tag = new Tag(10, false, context.getString(R.string.bbstag_tag10), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_10));
                break;
            case 11:
                tag = new Tag(11, false, context.getString(R.string.bbstag_tag11), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_11));
                break;
            case 15:
                tag = new Tag(15, false, context.getString(R.string.bbstag_tag15), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_15));
                break;
            case 17:
                tag = new Tag(17, false, context.getString(R.string.bbstag_tag17), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_17));
                break;
            case 18:
                tag = new Tag(18, false, context.getString(R.string.bbstag_tag18), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_18));
                break;
            case 19:
                tag = new Tag(19, false, context.getString(R.string.bbstag_tag19), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_19));
                break;
            case 20:
                tag = new Tag(20, false, context.getString(R.string.bbstag_tag20), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_20));
                break;
            case 21:
                tag = new Tag(21, false, context.getString(R.string.bbstag_tag21), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_21));
                break;
            case 22:
                tag = new Tag(22, false, context.getString(R.string.bbstag_tag22), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_22));
                break;
            case 23:
                tag = new Tag(23, false, context.getString(R.string.bbstag_tag23), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_23));
                break;
            case 24:
                tag = new Tag(24, false, context.getString(R.string.bbstag_tag24), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_24));
                break;
            case 25:
                tag = new Tag(25, false, context.getString(R.string.bbstag_tag25), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_25));
                break;
            case 26:
                tag = new Tag(26, false, context.getString(R.string.bbstag_tag26), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_26));
                break;
            case 27:
                tag = new Tag(27, false, context.getString(R.string.bbstag_tag27), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_27));
                break;
            case 28:
                tag = new Tag(28, false, context.getString(R.string.bbstag_tag28), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_28));
                break;


        }

        return tag;
    }
//
//    /*发布帖子的标签与我的标签是否有交集*/
//    public boolean isShowData(int publicTag[], int myTag[]) {
//        boolean isLoop = false;
//        if (publicTag != null && myTag != null) {
//            for (int i = 0; i < publicTag.length; i++) {
//                for (int j = 0; j < myTag.length; j++) {
//                    if (publicTag[i] == myTag[j]) {
//                        isLoop = true;
//                        break;
//                    }
//                }
//                if (isLoop)
//                    break;
//            }
//        } else {
//            isLoop = true;
//        }
//
//        return isLoop;
//    }
//
//    /*发布帖子的标签与我的标签是否有交集*/
//    public boolean isShowData(String publicTag[], int myTag[]) {
//
//        boolean isLoop = false;
//        if (publicTag != null && myTag != null) {
//            for (int i = 0; i < publicTag.length; i++) {
//                for (int j = 0; j < myTag.length; j++) {
//                    if (Integer.valueOf(publicTag[i]) == myTag[j]) {
//                        isLoop = true;
//                        break;
//                    }
//                }
//                if (isLoop)
//                    break;
//            }
//        } else {
//            isLoop = true;
//        }
//        return isLoop;
//    }
//
//
//    /***
//     * 创建标签
//     *
//     * @param context
//     */
    private void initTagList(Context context) {
        if (mTags == null || mTags.isEmpty()) {
            mTags = new ArrayList<Tag>();
            mTags.add(new Tag(1, false, context.getString(R.string.bbstag_tag1), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_1)));
            mTags.add(new Tag(2, false, context.getString(R.string.bbstag_tag2), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_2)));
            mTags.add(new Tag(20, false, context.getString(R.string.bbstag_tag20), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_20)));


            mTags.add(new Tag(6, false, context.getString(R.string.bbstag_tag6), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_6)));
            mTags.add(new Tag(21, false, context.getString(R.string.bbstag_tag21), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_21)));

            mTags.add(new Tag(23, false, context.getString(R.string.bbstag_tag23), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_23)));
            mTags.add(new Tag(9, false, context.getString(R.string.bbstag_tag9), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_9)));

//            mTags.add(new Tag(12, false, context.getString(R.string.bbstag_tag12), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_12)));
//            mTags.add(new Tag(13, false, context.getString(R.string.bbstag_tag13), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_13)));
//            mTags.add(new Tag(14, false, context.getString(R.string.bbstag_tag14), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_14)));
            mTags.add(new Tag(15, false, context.getString(R.string.bbstag_tag15), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_15)));
//            mTags.add(new Tag(16, false, context.getString(R.string.bbstag_tag16), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_16)));

            mTags.add(new Tag(4, false, context.getString(R.string.bbstag_tag4), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_4)));
            mTags.add(new Tag(5, false, context.getString(R.string.bbstag_tag5), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_5)));
            mTags.add(new Tag(18, false, context.getString(R.string.bbstag_tag18), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_18)));
            mTags.add(new Tag(19, false, context.getString(R.string.bbstag_tag19), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_19)));
            mTags.add(new Tag(28, false, context.getString(R.string.bbstag_tag28), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_28)));


            mTags.add(new Tag(3, false, context.getString(R.string.bbstag_tag3), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_3)));
            mTags.add(new Tag(7, false, context.getString(R.string.bbstag_tag7), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_7)));
            mTags.add(new Tag(24, false, context.getString(R.string.bbstag_tag24), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_24)));
            mTags.add(new Tag(17, false, context.getString(R.string.bbstag_tag17), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_17)));


            mTags.add(new Tag(26, false, context.getString(R.string.bbstag_tag26), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_26)));
            mTags.add(new Tag(8, false, context.getString(R.string.bbstag_tag8), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_8)));
            mTags.add(new Tag(22, false, context.getString(R.string.bbstag_tag22), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_22)));


            mTags.add(new Tag(27, false, context.getString(R.string.bbstag_tag27), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_27)));


            mTags.add(new Tag(25, false, context.getString(R.string.bbstag_tag25), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_25)));
            mTags.add(new Tag(10, false, context.getString(R.string.bbstag_tag10), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_10)));
            mTags.add(new Tag(11, false, context.getString(R.string.bbstag_tag11), context.getResources().getColor(R.color.bbs_tag_backgroud_normal_11)));
        }

    }

    public void initTagListConfig(Context context, int[] labelId) {
        initTagList(context);
        setTabSelected(mTags, labelId);
    }

    private void setTabSelected(List<Tag> mTags, int[] labelId) {

        if (labelId != null) {
            mTags.get(10).setChecked(false);

            for (int i = 0; i < labelId.length; i++) {
                for (Tag tag : mTags) {
                    if (tag.getId() == labelId[i]) {
                        tag.setChecked(true);
                        break;
                    }
                }
            }
        } else {
            mTags.get(23).setChecked(true);
        }
    }


    public void setViewDrawable(Context context, TextView localTagView, int colorList) {
        StateListDrawable drawable = new StateListDrawable();

        GradientDrawable gradientDrawable_normal = new GradientDrawable();
        gradientDrawable_normal.setColor(context.getResources().getColor(R.color.white));
        gradientDrawable_normal.setCornerRadius(QLScreenUtil.dpToPx(context, 3f));
        gradientDrawable_normal.setStroke((int) QLScreenUtil.dpToPx(context, 1f), colorList);

        GradientDrawable gradientDrawable_press = new GradientDrawable();
        gradientDrawable_press.setColor(colorList);
        gradientDrawable_press.setCornerRadius(QLScreenUtil.dpToPx(context, 3f));
        gradientDrawable_press.setStroke((int) QLScreenUtil.dpToPx(context, 1f), colorList);

        drawable.addState(new int[]{-android.R.attr.state_selected}, gradientDrawable_normal);
        drawable.addState(new int[]{android.R.attr.state_selected}, gradientDrawable_press);

        localTagView.setBackgroundDrawable(drawable);
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-android.R.attr.state_selected}, new int[]{android.R.attr.state_selected}}, new int[]{colorList, context.getResources().getColor(R.color.white)});
        localTagView.setTextColor(colorStateList);
    }

}
