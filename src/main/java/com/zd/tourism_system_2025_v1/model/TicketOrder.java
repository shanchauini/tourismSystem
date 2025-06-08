package com.zd.tourism_system_2025_v1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketOrder extends Order {
    private int attractionID;
    private int ticketID;

    @Override
    public boolean validate() {
        if (getUserID() <= 0) {
            return false;
        }
        if (ticketID <= 0) {
            return false;
        }
        if (attractionID <= 0) {
            return false;
        }
        if (getOrderType() == null || getOrderType().isEmpty()) {
            return false;
        }
        if (getTotalAmount() <= 0) {
            return false;
        }
        if (getCustomerName() == null || getCustomerName().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String getOrderDetails() {
        return String.format("门票订单 - 订单ID: %d, 用户ID: %d, 门票ID: %d, 景点ID: %d, 订单类型: %s, 总金额: %d, 状态: %s",
            getOrderID(), getUserID(), ticketID, attractionID, getOrderType(), getTotalAmount(), getStatus());
    }
}
