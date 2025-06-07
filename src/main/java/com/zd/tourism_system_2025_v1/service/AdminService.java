package com.zd.tourism_system_2025_v1.service;


import com.zd.tourism_system_2025_v1.DAO.AdminDAO;

import com.zd.tourism_system_2025_v1.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AdminService {
    //处理管理员业务逻辑

    private final AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO)
    {this.adminDAO = adminDAO;};

    //注册信息
    public boolean register(Admin admin) {
        try{

            if(validatePassword(admin.getPassword())==false)
                return false;
            int result = adminDAO.addAdmin(admin);
            return result > 0;//判断注册是否成功
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //登录
    public Admin login(int userID, String password) {
        try {
            Admin admin = adminDAO.getAdminByUserId(userID);
            if (admin != null && password.equals(admin.getPassword())) {
                return admin;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //修改个人信息
    public Boolean updatePassword(int userID, String password) {
        try{
            if(validatePassword(password)==false)
                return false;
            int result = adminDAO.updateAdmin(userID, password);
            return result > 0;//判断信息修改是否成功
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //密码判断逻辑
    public static boolean validatePassword(String password) {
        // 正则解释：
        // ^             - 字符串开始
        // (?=.*[a-zA-Z]) - 至少包含一个字母（大小写）
        // (?=.*\\d)     - 至少包含一个数字
        // .{4,16}       - 长度4-16个任意字符
        // $             - 字符串结束
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{4,16}$";
        return password != null && password.matches(regex);
    }

}
