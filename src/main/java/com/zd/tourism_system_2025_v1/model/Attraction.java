package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Attraction {
   private int  attractionID;
   private String attractionName;
   private String attractionLevel;
   private String attractionAddress;
   private String openingHours;
   private String introduction;
   private String  coverImage;

   List<Ticket> ticketList = new ArrayList<Ticket>();//组合模式？管理该景点下的门票
}
