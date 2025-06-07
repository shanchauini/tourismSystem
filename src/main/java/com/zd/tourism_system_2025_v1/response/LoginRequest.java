package com.zd.tourism_system_2025_v1.response;

import lombok.Data;

@Data
public class LoginRequest {
    private int userID;
    private String password;
}
