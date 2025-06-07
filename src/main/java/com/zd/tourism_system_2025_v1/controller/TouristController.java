package com.zd.tourism_system_2025_v1.controller;

import com.zd.tourism_system_2025_v1.model.Admin;
import com.zd.tourism_system_2025_v1.model.Tourist;
import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.response.LoginRequest;
import com.zd.tourism_system_2025_v1.service.AdminService;
import com.zd.tourism_system_2025_v1.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    //注册接口
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody Tourist tourist) {
        try{
            boolean isSuccess = touristService.register(tourist);
            if(isSuccess){
                return ResponseEntity.ok(
                        new ApiResponse(true,"普通用户注册成功",null)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(false,"普通用户注册失败",null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //登录接口
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Tourist tourist = touristService.login(
                    loginRequest.getUserID(),
                    loginRequest.getPassword()
            );

            if (tourist != null) {
                return ResponseEntity.ok(
                        new ApiResponse(true, "登录成功", tourist)
                );
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new ApiResponse(false, "用户名或密码错误", null)
                );
            }
        } catch (Exception e) {
            // 错误处理...
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //修改个人信息接口
    @PostMapping("/update_tourist_info")
    public ResponseEntity<ApiResponse> updateTouristInfo(@RequestBody Tourist tourist) {
        try{
            boolean isSuccess = touristService.updateTouristInfo(tourist);
            if(isSuccess){
                return ResponseEntity.ok(
                        new ApiResponse(true,"普通用户信息修改成功",null)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(false,"普通用户信息修改失败",null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

}
