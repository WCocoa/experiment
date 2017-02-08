package com.qiantang.neighbourmother.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author quliang
 * @version 2015-8-26 下午12:30:54
 *          desc 贴吧评论对象
 */
public class PCityObj implements Serializable {

    private static final long serialVersionUID = 1L;
            private int areaId;
        private String areaName;

    public PCityObj(int areaId,String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public PCityObj(int areaId) {
        this.areaId = areaId;
    }

    public PCityObj() {
    }

    private ArrayList<PDistrictObj> counties = new ArrayList<PDistrictObj>();

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



        public ArrayList<PDistrictObj> getCounties() {
            return counties;
        }

        public void setCounties(ArrayList<PDistrictObj> counties) {
            this.counties = counties;
        }
}
