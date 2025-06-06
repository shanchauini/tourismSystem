package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

@Data
public class Attraction {
   private int  attractionID;
   private String attractionName;
   private String attractionLevel;
   private String attractionAddress;
   private String openingHours;
   private String introduction;
   private String  coverImage;
}
