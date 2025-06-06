package com.zd.tourism_system_2025_v1.model;

import lombok.Data;

@Data
public class Tourist extends User{

    private String nickName;
    private String gender;
    private String phone;
    private String location;
}
