package com.qiantang.neighbourmother.business.response;

import com.qiantang.neighbourmother.model.OrderObj;
import com.qiantang.neighbourmother.model.ServTypeObj;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:专员列表请求接口响应参数
 * author: Cocoa
 * date: 2016/9/28.
 */

public class OrderDetailResp extends BaseResp implements Serializable {
    private OrderObj order;
    private AttacheDetailResp servant;

//    private String order_title;//	string 		订单内容
//    private String servant_id;//	string 		专员id
//    private int money;//	string 		金额
//    private String start_time;//	string 		开始日期
//    private String start_date;//	string 		开始时间
//    private String place_1;//	string 		接送地点　接
//    private String place_2;//	string 		接送地点　送
//    private String place_3;//string 		看护地点
//    private int order_status;//	string 		'0 待确认，1已确认待接单，2已接单待支付，3已支付等待服务开始　4开始服务　5服务完成　6订单取消 7订单失效',
//    private int tips;//	string 		消费
//    private int snack;//	string 		加餐数量
//
//    private String user_name;//	string 		姓名
//    private int user_gender;//string 		性别
//    private String user_avatar;//	string 		头像
//    private String user_age;//	string 		年龄
//    private String user_address;//	string 		地址
//    private String servant_type;//	string 		专员类型　
//    private String industry;//	string 		行业
//    private String[] servant_type_array_string;//	string 		专员类型
//    private double place_1_lat;//	否 	普通 	string 		接送地点(接)纬度
//    private double place_1_lng;//	否 	普通 	string 		接送地点(接)经度
//    private double place_2_lat;//	否 	普通 	string 		接送地点(送)纬度
//    private double place_2_lng;//	否 	普通 	string 		接送地点(送)经度
//
//    private String[] servant_type_array_id;//	string 		专员类型
//    private int phase_id;//	string 		学期阶段id
//    private String service_id;//	string 		服务id　如1,5,6
//    private int additional_money;//	string 		追加金额
//    private String additional_reason;//	string 		追加原因
//    private int additional_state;//	string 		追加金额支付状态　　0 未支付　１已支付　２正在处理
//    private int snack_money;//加餐单价
//    private int order_star;//打分评价
//
//    private long accept_time;//	string 		专员接受订单的时间
//    private long current_time;//	string 		现在时间
//    private String order_no;
//    private String order_id;
//    private String province_name;
//    private String city_name;
//    private String district_name;
//
//    private String province;
//    private String city;
//    private String district;
//    private long  ctime;
//    /**1早上送孩子2晚上接孩子3看护4辅导作业5周末看护*/
//    private int[] service_cate_id;
//    private List<ServTypeObj> service_tag_array;


    public OrderObj getOrder() {
        return order;
    }

    public void setOrder(OrderObj order) {
        this.order = order;
    }

    public AttacheDetailResp getServant() {
        return servant;
    }

    public void setServant(AttacheDetailResp servant) {
        this.servant = servant;
    }
}
