package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Order {
    //抽象产品

    private int orderID;
    private int userID;
    private String orderType;
    private int totalAmount;
    private String status;
    private LocalDateTime createTime;
    private String customerName;
    private String  customerPhone;

    // 抽象方法，由具体订单类实现
    public abstract boolean validate();
    public abstract String getOrderDetails();

    /*
    // 模板方法
    public final void processOrder() {
        //validateOrder();
        //calculatePayment();
        generateConfirmation();
    }

    //protected abstract void validateOrder();
    //protected abstract void calculatePayment();

    private void generateConfirmation() {
        System.out.println("订单确认已生成");
    }*/
}