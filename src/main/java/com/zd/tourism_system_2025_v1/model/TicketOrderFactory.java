package com.zd.tourism_system_2025_v1.model;

import com.zd.tourism_system_2025_v1.model.OrderFactory;
import org.springframework.stereotype.Component;

@Component
public class TicketOrderFactory implements OrderFactory {
    @Override
    public Order createOrder() {
        TicketOrder order = new TicketOrder();
        order.setStatus("COMMITTED");
        order.setCreateTime(java.time.LocalDateTime.now());
        return order;
    }
}