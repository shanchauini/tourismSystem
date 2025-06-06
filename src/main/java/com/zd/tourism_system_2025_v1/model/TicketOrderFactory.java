package com.zd.tourism_system_2025_v1.model;

public class TicketOrderFactory implements OrderFactory {
    @Override
    public Order createOrder(Order order) {
        //具体工厂，产生具体产品
        TicketOrder ticketorder = new TicketOrder();
        // 设置门票特有属性
        //ticketorder.setAttractionId(order.getAttractionId());
        //ticketorder.setTicketType(order.getTicketType());
        return ticketorder;
    }
}
