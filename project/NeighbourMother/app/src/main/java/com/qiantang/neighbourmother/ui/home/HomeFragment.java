package com.qiantang.neighbourmother.ui.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.adapter.HomeAdapter;
import com.qiantang.neighbourmother.business.data.HomeHttp;
import com.qiantang.neighbourmother.business.request.HomeReq;
import com.qiantang.neighbourmother.business.response.HomeResp;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.HomeCreateAdPager;
import com.qiantang.neighbourmother.logic.ListVewTopButton;
import com.qiantang.neighbourmother.logic.UserContacts;
import com.qiantang.neighbourmother.model.AdObj;
import com.qiantang.neighbourmother.model.HomeItemChildObj;
import com.qiantang.neighbourmother.model.LocInfoObj;
import com.qiantang.neighbourmother.model.ToAttacheDetailObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.BaseFragment;
import com.qiantang.neighbourmother.ui.dialog.AccountPayDialog;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.widget.refreshview.XListView;

import java.util.List;


/**
 * Created by quliang on 16-8-2.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    //    0不加载数据1去城市选择页面2加载数据
    public static int               behavior;
    private       XListView         xListView;
    private       HomeAdapter       homeAdapter;
    private       HomeCreateAdPager homeCreateAdPager;

    private HomeReq        homeReq;
    private LocInfoObj     cityObj;
    private UserInfoResp   userInfoResp;
    private RelativeLayout rl_home_apply;
    private ImageView      home_apply_teacher;
    private ImageView      return_top;

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }


    private DisplayMetrics displayMetrics = new DisplayMetrics();
    @Override
    public void initView(View view) {
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        xListView = (XListView) view.findViewById(R.id.xListView);
        xListView.setPullLoadEnable(false);
        home_center_loc = (TextView) view.findViewById(R.id.home_center_loc);
        ll_home_center_loc = (LinearLayout) view.findViewById(R.id.ll_home_center_loc);
        rl_home_apply = (RelativeLayout) view.findViewById(R.id.rl_home_apply);
        home_apply_teacher = (ImageView) view.findViewById(R.id.home_apply_teacher);
        return_top = (ImageView) view.findViewById(R.id.return_top);
        ((BaseActivity) getActivity()).calcuAdersWidth(rl_home_apply, 0.5f,displayMetrics.widthPixels);
        ((BaseActivity) getActivity()).calcuAdersWidth(home_apply_teacher, 0.25f,displayMetrics.widthPixels);
        homeAdapter = new HomeAdapter(getActivity());
        initHead();
    }

    @Override
    public void initData() {
        new ListVewTopButton(xListView, return_top);
        homeReq = new HomeReq();
        xListView.addHeaderView(head, null, false);
        xListView.setAdapter(homeAdapter);
        initCurAddress();


    }

    @Override
    public void failureOperation(Object obj) {
        super.failureOperation(obj);
        xListView.aotuRefreshComplete();
    }

    //    0不加载数据1去城市选择页面2加载数据
    @Override
    public void onResume() {
        super.onResume();
        AppLog.D("Home_onResume");
        if (homeCreateAdPager != null)
            homeCreateAdPager.onResume();
        switch (behavior) {
            case 0:
                break;
            case 1:
                behavior = 0;
                startActivityForResult(new Intent(getActivity(), CityListActivity.class), 1);
                break;
            case 2:
                behavior = 0;
//                userInfoResp=UserContacts.getUserInfo(getActivity());
                LocInfoObj temp_cityObj = ((NeighbourMotherApplication) getActivity().getApplication()).cur_locInfoObj;
                home_center_loc.setText(temp_cityObj.getNeighbourhoodName());
                getHttpData(true);
                break;
        }
    }

    boolean isShow   = true;
    boolean isFlag   = false;
    boolean isFirst  = true;
    boolean isScroll = true;
    private long startTime;
    private long endTime;

    @Override
    public void initEvent() {
        category1.setOnClickListener(this);
        category2.setOnClickListener(this);
        return_top.setOnClickListener(this);
//        category3.setOnClickListener(this);
//        category4.setOnClickListener(this);
        ll_home_center_loc.setOnClickListener(this);
        home_apply_teacher.setOnClickListener(this);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

//                    Intent intent = new Intent(getActivity(), RecyclerViewActivity.class);
                    Intent intent = new Intent(getActivity(), AttacheDetailsActivity.class);
                    intent.putExtra(IntentFinal.INTENT_ATTACHE_OBJ, new ToAttacheDetailObj(homeAdapter.getDataList().get(position - 2).getUser_id(), 1));
                    startActivity(intent);
                }
            }
        });
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getHttpData(true);
            }

            @Override
            public void onLoadMore() {
                getHttpData(false);
            }
        });


//        xListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, final int scrollState) {
//
//
//                if (scrollState == SCROLL_STATE_TOUCH_SCROLL && !isFlag) {
//                    isFlag = true;
//                    startTime = System.currentTimeMillis();
//                    AnimatorSet set = new AnimatorSet();
//                    set.playTogether(ObjectAnimator.ofFloat(rl_home_apply, "alpha", 1, 0.5f), ObjectAnimator.ofFloat(rl_home_apply, "translationX", 0, 100));
//                    set.start();
//                } else if (scrollState == SCROLL_STATE_IDLE && isFlag ) {
//                    isFlag = true;
//                    isShow = false;
//                    endTime = System.currentTimeMillis();
//
//                    handler.sendEmptyMessageDelayed(9, 1000);
//
//
//                }
//
//                if (scrollState == SCROLL_STATE_FLING) {
//                    isFlag = true;
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//
//            }
//
//
//        });

    }


    private void getHttpData(boolean isFirstLoad) {
        cityObj = ((NeighbourMotherApplication) getActivity().getApplication()).cur_locInfoObj;

        homeReq.setDistrict(String.valueOf(cityObj.getDistrict_id()));
        homeReq.setCommunity(cityObj.getNeighbourhoodName());
        homeReq.setOffset(isFirstLoad == true ? 0 : homeAdapter.getDataList().size());
        new HomeHttp(getActivity(), handler, homeReq, isFirstLoad == true ? 4 : 5);
    }


    private void setData(List<HomeItemChildObj> homeItemObjs, boolean isFirst, int orgLength) {
        isScroll = true;
        if (isFirst) {
            homeAdapter.getDataList().clear();
        }
        if (homeItemObjs != null && homeItemObjs.size() > 0) {

            homeAdapter.getDataList().addAll(homeItemObjs);
            if (orgLength < 10) {
                xListView.setPullLoadEnable(false);
            } else {
                xListView.setPullLoadEnable(true);
            }
        } else {
            ToastUtils.toastshort(getActivity(), isFirst ? getString(R.string.app_pull_not_data) : getString(R.string.app_pull_already_last_page));
        }
        homeAdapter.notifyDataSetChanged();
        xListView.aotuRefreshComplete();
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 3:
                int pos = msg.arg1;
                home_head_viewpager.setCurrentItem(pos, true);
                closeProgressDialog();
                break;
            case 4:
                HomeResp homeResp = (HomeResp) msg.obj;
                showAdImg(homeResp.getAd());
                int length = homeResp.getList().size();
                setData(homeResp.getList(), true, length);
                closeProgressDialog();
                break;
            case 5:
                HomeResp homeResp1 = (HomeResp) msg.obj;
                int length1 = homeResp1.getList().size();
                setData(homeResp1.getList(), false, length1);
                closeProgressDialog();
                break;
            case 9:
                isFlag = false;
                AnimatorSet set = new AnimatorSet();
                set.playTogether(ObjectAnimator.ofFloat(rl_home_apply, "alpha", 0.5f, 1), ObjectAnimator.ofFloat(rl_home_apply, "translationX", 100, 0));
                set.start();
                break;


        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.category1:
                intent = new Intent(getActivity(), ServiceIntroduceActivity.class);
                intent.putExtra(IntentFinal.INTENT_HOME_SERVICE, 2);
                startActivity(intent);
                break;
            case R.id.category2:
                intent = new Intent(getActivity(), ServiceIntroduceActivity.class);
                intent.putExtra(IntentFinal.INTENT_HOME_SERVICE, 1);
                startActivity(intent);
                break;
//            case R.id.category3:
//                intent = new Intent(getActivity(), ServiceIntroduceActivity.class);
//                intent.putExtra(IntentFinal.INTENT_HOME_SERVICE, 3);
//                startActivity(intent);
//                break;
//            case R.id.category4:
////                initDialog();
//                intent = new Intent(getActivity(), ServiceIntroduceActivity.class);
//                intent.putExtra(IntentFinal.INTENT_HOME_SERVICE, 4);
//                startActivity(intent);
//                break;
            case R.id.ll_home_center_loc:
                startActivityForResult(new Intent(getActivity(), CityListActivity.class), 1);
                break;
            case R.id.home_apply_teacher:
                startActivity(new Intent(getActivity(), TeacherToJoinActivity.class));
                break;
            case R.id.return_top:
                xListView.setSelection(0);
                break;
        }
    }


    private AccountPayDialog accountPayDialog;

    private void initDialog() {
        if (accountPayDialog == null)
            accountPayDialog = new AccountPayDialog();
        accountPayDialog.show(getChildFragmentManager(), "accountPayDialog");
    }

    private View         head;
    private ViewPager    home_head_viewpager;
    private LinearLayout ad_point_ll;
    private TextView     home_center_loc;

    private LinearLayout ll_home_center_loc;
    private LinearLayout category1;
    private LinearLayout category2;
//    private LinearLayout category3;
//    private LinearLayout category4;


    private void initHead() {
        head = getActivity().getLayoutInflater().inflate(R.layout.layout_home_head, null);
        home_head_viewpager = (ViewPager) head.findViewById(R.id.home_head_viewpager);
        ad_point_ll = (LinearLayout) head.findViewById(R.id.ad_point_ll);
        category1 = (LinearLayout) head.findViewById(R.id.category1);
        category2 = (LinearLayout) head.findViewById(R.id.category2);
//        category3 = (LinearLayout) head.findViewById(R.id.category3);
//        category4 = (LinearLayout) head.findViewById(R.id.category4);

        ((BaseActivity) getActivity()).calcuAdersWidth(home_head_viewpager, 0.5f,displayMetrics.widthPixels);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (resultCode){
//            case 2:
//                LocInfoObj cityObj=((NeighbourMotherApplication) getActivity().getApplication()).cur_locInfoObj;
//                AppLog.D("cityObj.getName():"+cityObj.getCity_name());
//                AppLog.D("cityObj.getpDistrictObj().getAreaName():"+cityObj.getDistrict_name());
//                AppLog.D("cityObj.getNeighbourhoodName():"+cityObj.getNeighbourhoodName());
//
//                home_center_loc.setText(cityObj.getNeighbourhoodName());
//                getHttpData(true);
//                break;
//        }
    }

    private void showAdImg(List<AdObj> adObjs) {
        if (adObjs != null && adObjs.size() > 0) {
            if (homeCreateAdPager == null)
                homeCreateAdPager = new HomeCreateAdPager(getActivity(),
                        ad_point_ll, home_head_viewpager, handler,
                        3, true);
            homeCreateAdPager.start(adObjs);
        }
    }


    @Override
    public void onPause() {
        if (homeCreateAdPager != null)
            homeCreateAdPager.onPause();
        super.onPause();
    }

//    private void link(ArrayList<HomeItemChildObj> homeItemChildObjs){
//        if(homeItemChildObjs!=null&&homeItemChildObjs.size()>0){
//
//            int length=homeAdapter.getDataList().size();
//            if(length>0){
//                HomeItemObj homeItemObj=homeAdapter.getDataList().get(length-1);
//
//                if(homeItemObj.getChildObj2()==null){
//                    homeItemObj.setChildObj2(homeItemChildObjs.get(0));
//                    homeItemChildObjs.remove(0);
//
//                    if(homeItemObj.getChildObj3()==null&&homeItemChildObjs.size()>0){
//                        homeItemObj.setChildObj3(homeItemChildObjs.get(0));
//                        homeItemChildObjs.remove(0);
//                        homeAdapter.notifyDataSetChanged();
//                    }
//                    homeAdapter.notifyDataSetChanged();
//
//                }else if(homeItemObj.getChildObj3()==null){
//                    homeItemObj.setChildObj3(homeItemChildObjs.get(0));
//                    homeItemChildObjs.remove(0);
//                    homeAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }
//
//    private ArrayList<HomeItemObj> DataConversion(ArrayList<HomeItemChildObj> homeItemChildObjs, boolean isFirst){
//        if(!isFirst)
//        link(homeItemChildObjs);
//        ArrayList<HomeItemObj> homeItemObjs=null;
//        if(homeItemChildObjs!=null&&homeItemChildObjs.size()>0){
//            int length=homeItemChildObjs.size();
//            homeItemObjs=new ArrayList<HomeItemObj>();
//
//            HomeItemObj homeItemObj=null;
//            for (int i=0;i<length;i++){
//
//                switch (i%3){
//                    case 0:
//                        homeItemObj=new HomeItemObj();
//                        homeItemObjs.add(homeItemObj);
//                        homeItemObj.setChildObj1(homeItemChildObjs.get(i));
//                        break;
//                    case 1:
//                        homeItemObj.setChildObj2(homeItemChildObjs.get(i));
//                        break;
//                    case 2:
//                        homeItemObj.setChildObj3(homeItemChildObjs.get(i));
//                        break;
//                }
//            }
//        }
//
//return homeItemObjs;
//    }

    private void initCurAddress() {
        UserInfoResp userInfoResp = UserContacts.getUserInfo(getActivity());

        if (userInfoResp.getProvince() != 0 && userInfoResp.getCity() != 0 && userInfoResp.getDistrict() != 0 && !TextUtils.isEmpty(userInfoResp.getUser_community())) {

            LocInfoObj locInfoObj = new LocInfoObj();
            locInfoObj.setProvince_id(userInfoResp.getProvince());
            locInfoObj.setProvince_name(userInfoResp.getProvince_name());
            locInfoObj.setCity_id(userInfoResp.getCity());
            locInfoObj.setCity_name(userInfoResp.getCity_name());
            locInfoObj.setDistrict_id(userInfoResp.getDistrict());
            locInfoObj.setDistrict_name(userInfoResp.getDistrict_name());
            locInfoObj.setNeighbourhoodName(userInfoResp.getUser_community());

            ((NeighbourMotherApplication) getActivity().getApplication()).cur_locInfoObj = locInfoObj;

            home_center_loc.setText(userInfoResp.getUser_community());
            behavior = 2;
        } else {
            if (((NeighbourMotherApplication) getActivity().getApplication()).cur_locInfoObj == null)
                ((NeighbourMotherApplication) getActivity().getApplication()).getAppCurrLoc();
            ToastUtils.toastLong(getActivity(), R.string.homefrag_please_finish_neighbourhood_info);
            behavior = 1;
        }
    }


}