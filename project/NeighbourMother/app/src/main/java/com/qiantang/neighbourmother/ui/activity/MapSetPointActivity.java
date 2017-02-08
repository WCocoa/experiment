package com.qiantang.neighbourmother.ui.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.MapSetPointObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.util.ArrayList;
import java.util.List;


public class MapSetPointActivity extends BaseActivity implements View.OnClickListener {


    private ImageView back;//返回
    private MapView bmapView;//地图
    //    private TextView btn_search;//点击搜索
    private BaiduMap mBaiduMap;
    //    private EditText map_search;//搜索内容输入框
    private TextView map_setpoint_complete;//完成
    public MyLocationListenner myListener;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    // 定位相关
    LocationClient mLocClient;
    boolean isFirstLoc = true; // 是否首次定位

    //检索
//    PoiSearch mPoiSearch;
    private String city = "西安";
    private MapSetPointObj mapSetPointObj;
    private boolean flag;

    private int resultCode;

    @Override
    public int getContentView() {
        return R.layout.activity_map_set_point;
    }

    @Override
    public void initView() {
        bmapView = (MapView) findViewById(R.id.bmapView);

        back = (ImageView) findViewById(R.id.back);
        map_setpoint_complete = (TextView) findViewById(R.id.map_setpoint_complete);
    }

    @Override
    public void initData() {

        resultCode = getIntent().getIntExtra(IntentFinal.INTENT_MAP_CHOICE_POINT_RESULT, 5);
        mapSetPointObj = (MapSetPointObj) getIntent().getSerializableExtra(IntentFinal.INTENT_MAP_SET_POINT);
        flag = getIntent().getBooleanExtra(IntentFinal.INTENT_ORDER_MAP_SET_POINT, false);
        if (flag) {
            map_setpoint_complete.setVisibility(View.INVISIBLE);
        } else {
            map_setpoint_complete.setVisibility(View.VISIBLE);
        }
        myListener = new MyLocationListenner();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap = bmapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);
        option.setProdName("DEMO");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocClient.setLocOption(option);
        mLocClient.start();
        if (mLocClient != null && !mLocClient.isStarted()) {
            mLocClient.requestLocation();
            mLocClient.start();
        }


        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

//        mPoiSearch = PoiSearch.newInstance();
//        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);


    }


    BDLocation mlocation;

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            location.setRadius(0);
            mlocation = location;
            if (mapSetPointObj == null) {
                mapSetPointObj = new MapSetPointObj();
            }

            if (mapSetPointObj.getLongitude() != 0 && mapSetPointObj.getLatutide() != 0) {

                mlocation.setLongitude(mapSetPointObj.getLongitude());
                mlocation.setLatitude(mapSetPointObj.getLatutide());

                AppLog.D("mapSetPointObj.getLongitude():" + mapSetPointObj.getLongitude());
                AppLog.D("mapSetPointObj.getLatutide():" + mapSetPointObj.getLatutide());
            }
//
//            AppLog.D("mapSetPointObj.getLongitude()1:" + mapSetPointObj.getLongitude());
//            AppLog.D("mapSetPointObj.getLatutide()1:" + mapSetPointObj.getLatutide());
            if (!flag)
                mBaiduMap.setOnMapClickListener(listener);
            setMapLocal(mlocation);

            mLocClient.unRegisterLocationListener(myListener);
        }


    }

    /**
     * 定位SDK监听函数
     */
   /* public class MyLocationListener implements BDLocationListener {
            if (location == null || bmapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        @Override
        public void onReceiveLocation(BDLocation location) {
            AppLog.D("location.getLocType():" + location.getLocType() + "");

            location.setRadius(0);
            mlocation = location;
            if (mapSetPointObj != null && mapSetPointObj.getLongitude() > 0 && mapSetPointObj.getLatutide() > 0) {
                mlocation.setLongitude(mapSetPointObj.getLongitude());
                mlocation.setLatitude(mapSetPointObj.getLatutide());
            }
            if (isflag) {
                setMapLocal(mlocation);
                isflag = false;
            }
            mBaiduMap.setOnMapClickListener(listener);
        }
        public void onReceivePoi(BDLocation poiLocation) {}
    }*/
    private void setMapLocal(BDLocation location) {

//        ToastUtils.toastshort(this, "纬度：latLng.latitude:" + location.getLatitude());
//        ToastUtils.toastshort(this, "经度：latLng.longitude:" + location.getLongitude());


        if (location == null || bmapView == null) {
            return;
        }
        if (location.getLatitude() <1 ||location.getLongitude() <1) {
            location.setLatitude(34.2656910000);
            location.setLongitude(108.9534630000);
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

        mBaiduMap.setMyLocationData(locData);

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.map_setpoint);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);

        mBaiduMap.setMyLocationConfigeration(config);
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());

        mapSetPointObj.setLatutide(location.getLatitude());
        mapSetPointObj.setLongitude(location.getLongitude());
        AppLog.D("location.getLatitude()" + location.getLatitude() + "location.getLongitude():" + location.getLongitude());

        MapStatus.Builder builder = new MapStatus.Builder();
        if (isFirstLoc) {
            builder.target(ll).zoom(18.0f);
            isFirstLoc = false;
        }
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    public void initEvent() {
//        btn_search.setOnClickListener(this);
        map_setpoint_complete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mlocation.setLatitude(latLng.latitude);
            mlocation.setLongitude(latLng.longitude);

            setMapLocal(mlocation);
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    };
    List<PoiInfo> poiInfoList = new ArrayList<>();
    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        public void onGetPoiResult(PoiResult result) {
            AppLog.D("检索结果：" + result.getAllPoi());
            poiInfoList = (List<PoiInfo>) result.getAllPoi();
            LatLng latLng = poiInfoList.get(0).location;

            mlocation.setLatitude(latLng.latitude);
            mlocation.setLongitude(latLng.longitude);
            setMapLocal(mlocation);
            //获取POI检索结果
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//            AppLog.D("检索结果："+poiIndoorResult.getmArrayPoiInfo());
        }
    };

    @Override
    protected void updateUI(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_search:
//                mPoiSearch.searchInCity((new PoiCitySearchOption())
//                        .city(city)
//                        .keyword(map_search.getText().toString().trim())
//                        .pageNum(10));
//                break;

            case R.id.back:
                isFirstLoc = true;
                finish();
                break;

            case R.id.map_setpoint_complete:
                isFirstLoc = true;
                Intent intent = new Intent();

//                String latlon = (mlocation.getLatitude()+","+mlocation.getLongitude()+"");
//                AppLog.D("latlon:"+latlon);
                intent.putExtra(IntentFinal.INTENT_MAP_SET_POINT, mapSetPointObj);
                setResult(resultCode, intent);

                finish();

                break;
        }
    }

//    List<PoiInfo> poiInfoList = new ArrayList<>();
//    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
//        public void onGetPoiResult(PoiResult result) {
//            AppLog.D("检索结果：" + result.getAllPoi());
//            poiInfoList = (List<PoiInfo>) result.getAllPoi();
//            LatLng latLng = poiInfoList.get(0).location;
//
//            mlocation.setLatitude(latLng.latitude);
//            mlocation.setLongitude(latLng.longitude);
//            setMapLocal(mlocation);
//            //获取POI检索结果
//        }
//
//        public void onGetPoiDetailResult(PoiDetailResult result) {
//            //获取Place详情页检索结果
//        }
//
//        @Override
//        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
////            AppLog.D("检索结果："+poiIndoorResult.getmArrayPoiInfo());
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        bmapView.onDestroy();
//        mPoiSearch.destroy();
        bmapView = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }
}
