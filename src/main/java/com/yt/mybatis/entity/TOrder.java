package com.yt.mybatis.entity;

public class TOrder extends BaseEntry{
    private Integer orderId;
    private String orderTime;
    private String orderMoney;
    private Integer orderStatus;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "orderId=" + orderId +
                ", orderTime='" + orderTime + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
