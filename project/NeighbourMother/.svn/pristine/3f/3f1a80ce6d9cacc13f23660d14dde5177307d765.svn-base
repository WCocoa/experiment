package com.qiantang.neighbourmother.logic;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.qiantang.neighbourmother.util.AppLog;


/**
 * Created by Administrator on 2015/12/17.
 */
public class LocationInfo {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private static LocationInfo locationInfo = new LocationInfo();

    private LocationInfo() {
    }

    public static LocationInfo getInstance() {
        return locationInfo;
    }

    public void initLocation(Context context) {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(context);
            AppLog.D("开始定位");
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setScanSpan(2000);
            locationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            locationClientOption.setOpenGps(true);//可选，默认false,设置是否使用gps
            locationClientOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            locationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            locationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            locationClientOption.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
            locationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            locationClientOption.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
            mLocationClient.setLocOption(locationClientOption);
            mLocationClient.registerLocationListener(myListener);
            mLocationClient.start();
//            AppLog.D("mLocationClient:");
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
//            StringBuffer sb = new StringBuffer(256);

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

//                sb.append(location.getAddrStr());


            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果

//                sb.append(location.getAddrStr()+" "+location.getProvince() +" " +location.getCity() + " " +location.getDistrict());

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果

//                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {

//                sb.append("服务端网络定位失败");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }

//            AppLog.D("location.getLongitude():" + location.getLongitude());
//            AppLog.D("location.getLatitude():" + location.getLatitude());
//            AppLog.D("location.getCity():" + location.getCity());

            if (locationLisenter != null){
                String city=location.getCity();
                if(city!=null&&!city.equals("")){
                    city=city.substring(0,city.length()-1);
                    locationLisenter.loc(location.getProvince(), city, location.getDistrict(), location.getLongitude(), location.getLatitude());
                    mLocationClient.unRegisterLocationListener(myListener);
                    mLocationClient.stop();

                }

            }

        }
    }

    private LocationLisenter locationLisenter;

    public void setOnLocation(LocationLisenter mLocationLisenter) {
        this.locationLisenter = mLocationLisenter;
    }

    public interface LocationLisenter {
        void loc(String provice, String city, String district, double longitude, double Latitude);
    }

}
