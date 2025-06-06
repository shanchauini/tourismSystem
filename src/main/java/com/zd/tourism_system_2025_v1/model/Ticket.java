package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

@Data
public class Ticket {
    private int ticketID;
    private int attractionID;
    private String type;
    private int price;
    private int stock;
    private String description;
}
