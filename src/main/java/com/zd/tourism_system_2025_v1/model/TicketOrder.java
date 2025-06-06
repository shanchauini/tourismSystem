package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

@Data
public class TicketOrder extends Order {
    private String attractionID;
    private String ticketID;

    /*
    // 建造者模式
    public static class Builder {
        private TicketOrder order = new TicketOrder();

        public Builder setAttraction(String name) {
            order.attractionName = name;
            return this;
        }
        // 其他构建方法...
    }
*/

}
