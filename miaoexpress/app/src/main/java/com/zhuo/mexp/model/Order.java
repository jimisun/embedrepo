package com.zhuo.mexp.model;

public class Order {
    //订单编号
    private  int order_id;
    //用户id
    private  String user_id;
    //配送员id
    private String distributor_id;
    //订单类型
    private String order_distribut_type;
    //配送价格
    private Double order_price;
    //收货人姓名
    private String order_receiver_name;
    //收货人地址
    private String order_receiver_address;
    //收货人电话
    private Long order_receiver_tel;
    //下单时间
    private  String order_time;
    //送达时间
    private  String order_delivery_time;
    //订单状态
    private  int order_status;
    //订单备注
    private  String order_notes;
    //订单图片路径
    private String order_picpath;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getOrder_distribut_type() {
        return order_distribut_type;
    }

    public void setOrder_distribut_type(String order_distribut_type) {
        this.order_distribut_type = order_distribut_type;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public String getOrder_receiver_name() {
        return order_receiver_name;
    }

    public void setOrder_receiver_name(String order_receiver_name) {
        this.order_receiver_name = order_receiver_name;
    }

    public String getOrder_receiver_address() {
        return order_receiver_address;
    }

    public void setOrder_receiver_address(String order_receiver_address) {
        this.order_receiver_address = order_receiver_address;
    }

    public Long getOrder_receiver_tel() {
        return order_receiver_tel;
    }

    public void setOrder_receiver_tel(Long order_receiver_tel) {
        this.order_receiver_tel = order_receiver_tel;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_delivery_time() {
        return order_delivery_time;
    }

    public void setOrder_delivery_time(String order_delivery_time) {
        this.order_delivery_time = order_delivery_time;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_notes() {
        return order_notes;
    }

    public void setOrder_notes(String order_notes) {
        this.order_notes = order_notes;
    }

    public String getOrder_picpath() {
        return order_picpath;
    }

    public void setOrder_picpath(String order_picpath) {
        this.order_picpath = order_picpath;
    }
}
