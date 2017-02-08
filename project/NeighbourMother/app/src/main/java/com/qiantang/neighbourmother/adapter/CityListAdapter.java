package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.NeighbourMotherApplication;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.CityObj;
import com.qiantang.neighbourmother.model.LocInfoObj;
import com.qiantang.neighbourmother.sqlite.CityOperation;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by next on 2016/3/25.
 */
public class CityListAdapter extends BaseAdapter {
    private Activity context;
    private LayoutInflater inflater;
    private List<CityObj> allCities;
//    private List<CityObj> hotCities;
//    private List<String> historyCities;
    private String[] firstLetterArray;// 存放存在的汉语拼音首字母
    private Map<String, Integer> letterIndex;
    private final int VIEW_TYPE = 5;
    private CityOperation cityOperation;
    private Handler handler;

    private CityObj locCityObj;
    public CityListAdapter(Activity context, CityOperation cityOperation, List<CityObj> allCities, List<CityObj> hotCities, List<String> historyCities, Map<String, Integer> letterIndex, Handler handler) {
        this.handler = handler;
        this.context = context;
        this.cityOperation = cityOperation;
        this.allCities = allCities;
//        this.hotCities = hotCities;
//        this.historyCities = historyCities;
        this.letterIndex = letterIndex;
        inflater = LayoutInflater.from(this.context);
        locCityObj=allCities.get(0);
        setup();
    }

    private void setup() {
        firstLetterArray = new String[allCities.size()];
        for (int i = 0; i < allCities.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = getAlpha(allCities.get(i).getPinyin());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? getAlpha(allCities.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(allCities.get(i).getPinyin());
                letterIndex.put(name, i);
                firstLetterArray[i] = name;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return allCities.size();
    }

    @Override
    public Object getItem(int position) {
        return allCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int viewType = getItemViewType(position);
       final CityObj cityObj= allCities.get(position);
        Holder holder = new Holder();
        if (viewType == 0) {//定位

if(convertView==null){
                convertView = inflater.inflate(R.layout.item_city_location, null);
                holder.tv_location = (Button) convertView.findViewById(R.id.tv_location);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
            holder.tv_location.setText(cityObj.getName());
        }
//        else if (viewType == 1) {//最近访问
//            convertView = inflater.inflate(R.layout.item_city_grid, null);
//            GridView recentCityView = (GridView) convertView.findViewById(R.id.grid_city);
//            recentCityView.setAdapter(new RecentCityAdapter(context, this.historyCities));
//            recentCityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, historyCities.get(position), Toast.LENGTH_SHORT).show();
//                }
//            });
//            TextView recentHint = (TextView) convertView.findViewById(R.id.recentHint);
//            recentHint.setText("最近访问的城市");
//        }
//        else if (viewType == 2) {//热门城市
//            convertView = inflater.inflate(R.layout.item_city_grid, null);
//            final GridView hotCity = (GridView) convertView.findViewById(R.id.grid_city);
//            hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, hotCities.get(position).getName(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//            hotCity.setAdapter(new HotCityAdapter(context, this.hotCities));
//            TextView hotHint = (TextView) convertView.findViewById(R.id.recentHint);
//            hotHint.setText("热门城市");
//        }
//        else if (viewType == 3) {
//            convertView = inflater.inflate(R.layout.item_city_total_tag, null);
//        }
        else {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_city_list, null);
                holder = new Holder();
                holder.rela = (LinearLayout) convertView.findViewById(R.id.rela);
                holder.letter = (TextView) convertView.findViewById(R.id.tv_letter);
                holder.name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            if (position >= 1) {
                holder.name.setText(cityObj.getName());
                String currentStr = getAlpha(cityObj.getPinyin());
                String previewStr = (position - 1) >= 0 ? getAlpha(allCities.get(position - 1).getPinyin()) : " ";
                if (!previewStr.equals(currentStr)) {
                    holder.rela.setVisibility(View.VISIBLE);
                    holder.letter.setText(currentStr);
                } else {
                    holder.rela.setVisibility(View.GONE);
                }
            }
        }

        MyOnClick myOnClick=new MyOnClick(position,cityObj);
        if (viewType == 0) {
            holder.tv_location.setOnClickListener(myOnClick);
        }

        convertView.setOnClickListener(myOnClick);
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppLog.D("setOnClickListener_position:"+position);
//
//                if(position==0){
//                    ((EduApplication) context.getApplication()).curCityObj=((EduApplication) context.getApplication()).locCityObj;
//                }else{
//                    if(cityObj.get_id()==locCityObj.get_id())
//                        ((EduApplication) context.getApplication()).curCityObj=((EduApplication) context.getApplication()).locCityObj;
//else
//                        ((EduApplication) context.getApplication()).curCityObj=cityObj;
//                }
//                context.startService(new Intent(context, BindPushService.class));
//                context.setResult(2);
//                context.finish();
//            }
//        });

        return convertView;
    }

    private class MyOnClick implements  View.OnClickListener{
        private int position;
        private  CityObj cityObj;
        MyOnClick(int position,CityObj cityObj){
            this.position=position;
            this.cityObj=cityObj;
        }
        @Override
        public void onClick(View v) {
            if(position==0){
                ((NeighbourMotherApplication) context.getApplication()).cur_locInfoObj=((NeighbourMotherApplication) context.getApplication()).locInfoObj;
            }else{
                if(cityObj.get_id()==locCityObj.get_id())
                    ((NeighbourMotherApplication) context.getApplication()).cur_locInfoObj=((NeighbourMotherApplication) context.getApplication()).locInfoObj;
                else{

                    LocInfoObj locInfoObj=((NeighbourMotherApplication) context.getApplication()).cur_locInfoObj;

                    locInfoObj.setProvince_id(cityObj.getProvice_id());
                    locInfoObj.setProvince_name(cityOperation.findProvinceById(cityObj.getProvice_id()).getAreaName());
                    locInfoObj.setCity_id(cityObj.get_id());
                    locInfoObj.setCity_name(cityObj.getName());

                    ((NeighbourMotherApplication) context.getApplication()).cur_locInfoObj=locInfoObj;
                }
            }

            handler.sendEmptyMessage(1);
        }
    }

    class Holder {
        TextView letter, name;
        LinearLayout rela;
        Button tv_location;
    }


    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "定位";
        }
        else if (str.equals("1")) {
            return "最近";
        }
        else if (str.equals("2")) {
            return "热门";
        }
        else if (str.equals("3")) {
            return "全部";
        }
        else {
            return "#";
        }
    }

}
