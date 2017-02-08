package com.qiantang.neighbourmother.model;

import java.io.Serializable;

/**
 * @author quliang
 * @version 2015-8-26 下午12:30:54
 *          desc 贴吧评论对象
 */
public class PDistrictObj implements Serializable {

    private static final long serialVersionUID = 1L;
            private int areaId;
        private String areaName;

    public PDistrictObj(int areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public PDistrictObj(int areaId) {
        this.areaId = areaId;
    }

    public PDistrictObj() {
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
}
