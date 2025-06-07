package com.zd.tourism_system_2025_v1.controller;

import com.zd.tourism_system_2025_v1.model.Admin;
import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.response.LoginRequest;
import com.zd.tourism_system_2025_v1.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    /**
     * 管理员注册接口
     * POST /api/admin/register
     *
     * @param admin 管理员信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody Admin admin){
        try{
            boolean isSuccess = adminService.register(admin);
            if(isSuccess){
                return ResponseEntity.ok(
                        new ApiResponse(true,"管理员注册成功",null)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(false,"管理员注册失败",null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 管理员登录接口
     * POST /api/admin/login
     *
     * @param loginRequest 登录请求体
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Admin admin = adminService.login(
                    loginRequest.getUserID(),
                    loginRequest.getPassword()
            );

            if (admin != null) {
                return ResponseEntity.ok(
                        new ApiResponse(true, "登录成功", admin)
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

    //简单测试点
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //管理员修改密码接口
    @PostMapping("/update_password")
    public ResponseEntity<ApiResponse> register(@RequestBody LoginRequest loginRequest){
        try{
            boolean isSuccess = adminService.updatePassword(loginRequest.getUserID(), loginRequest.getPassword());
            if(isSuccess){
                return ResponseEntity.ok(
                        new ApiResponse(true,"管理员密码修改成功",null)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse(false,"管理员密码修改失败",null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

}
