package com.qiantang.neighbourmother.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author quliang
 * @version 2015-8-26 下午12:30:54
 *          desc 贴吧评论对象
 */
public class PProvinceObj implements Serializable {

    private static final long serialVersionUID = 1L;
            private int areaId;
        private String areaName;
        private ArrayList<PCityObj> cities = new ArrayList<PCityObj>();

    public PProvinceObj(int areaId,String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public PProvinceObj(int areaId) {
        this.areaId = areaId;
    }

    public PProvinceObj() {
    }

    public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }


        public ArrayList<PCityObj> getCities() {
            return cities;
        }

        public void setCities(ArrayList<PCityObj> cities) {
            this.cities = cities;
        }
}
