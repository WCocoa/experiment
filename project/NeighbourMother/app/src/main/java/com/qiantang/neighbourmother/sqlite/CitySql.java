package com.qiantang.neighbourmother.sqlite;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/9/2.
 */
public class CitySql {
    public static final String DB_NAME = "zhifu.db";
    public static final String PROVINCE_TABLE_NAME = "a_province";//省
    public static final String PROVINCE_ID = "_id";//省
    public static final String PROVINCE_NAME = "province_name";
    public static final String PROVINCE_STATUS = "status";

    public static final String CITY_TABLE_NAME = "a_city_copy";//市
    public static final String CITY_ID = "_id";//省
    public static final String CITY_NAME = "city_name";
    public static final String CITY_PROVINCE_ID = "province_id";
    public static final String PINYIN = "pinyin";

    public static final String DISTRICT_TABLE_NAME = "a_district";//区
    public static final String DISTRICT_ID= "_id";
    public static final String DISTRICT_NAME= "district_name";
    public static final String DISTRICT_CITY_ID= "city_id";



}
