package com.zhuo.mexp.model;

public class Distributor {
    //配送员id
    private String distributor_id;
    //配送员密码
    private String distributor_password;
    //配送员姓名
    private  String distributor_name;
    //配送员idcar
    private  String distributor_idcar;
    //配送员电话
    private  Long distributor_tel;
    //配送员账户余额
    private Double distributor_money;
    //账号状态
    private int  distributor_status;
    //账号头像
    private  String distributor_picPath;
    //接单次数
    private  int distributor_singularnum;

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getDistributor_password() {
        return distributor_password;
    }

    public void setDistributor_password(String distributor_password) {
        this.distributor_password = distributor_password;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public String getDistributor_idcar() {
        return distributor_idcar;
    }

    public void setDistributor_idcar(String distributor_idcar) {
        this.distributor_idcar = distributor_idcar;
    }

    public Long getDistributor_tel() {
        return distributor_tel;
    }

    public void setDistributor_tel(Long distributor_tel) {
        this.distributor_tel = distributor_tel;
    }

    public Double getDistributor_money() {
        return distributor_money;
    }

    public void setDistributor_money(Double distributor_money) {
        this.distributor_money = distributor_money;
    }

    public int getDistributor_status() {
        return distributor_status;
    }

    public void setDistributor_status(int distributor_status) {
        this.distributor_status = distributor_status;
    }

    public String getDistributor_picPath() {
        return distributor_picPath;
    }

    public void setDistributor_picPath(String distributor_picPath) {
        this.distributor_picPath = distributor_picPath;
    }

    public int getDistributor_singularnum() {
        return distributor_singularnum;
    }

    public void setDistributor_singularnum(int distributor_singularnum) {
        this.distributor_singularnum = distributor_singularnum;
    }
}
