package com.zd.tourism_system_2025_v1.service;

import com.zd.tourism_system_2025_v1.DAO.TouristDao;
import com.zd.tourism_system_2025_v1.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TouristService {
    //处理管理员业务逻辑

    private final TouristDao touristDao;

    @Autowired
    public TouristService(TouristDao touristDao)
    {this.touristDao = touristDao;};

    //普通用户注册信息
    public boolean register(Tourist tourist) {
        try{
            if(validateTouristInfo(tourist)==false) {
                System.out.println("Tourist Info is not valid");
                return false;}
            if(validatePassword(tourist.getPassword())==false) {
                System.out.println("Password is not valid");
                return false;
            }
            int result = touristDao.addTourist(tourist);
            return result > 0;//判断注册是否成功
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //用户登录
    public Tourist login(int userID, String password) {
        try {
            Tourist tourist = touristDao.getTouristByUserId(userID);
            if (tourist != null && password.equals(tourist.getPassword())) {
                return tourist;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //用户修改个人信息
    public Boolean updateTouristInfo(Tourist tourist) {
        try{

            if(validateTouristInfo(tourist)==false)
                return false;
            if(validatePassword(tourist.getPassword())==false)
                return false;
            int result = touristDao.updateTourist(tourist);
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
    public static boolean validateTouristInfo(Tourist tourist) {
        //判断用户输入的个人信息是否有效
        //3+4属性，id不用判断，密码有单独逻辑，类型不用判断
        if(tourist.getGender().isEmpty())
            return false;
        if(tourist.getNickName().isEmpty())
            return false;
        if(tourist.getPhone().length()!=11)
            return false;
        for(char c:tourist.getPhone().toCharArray())
        {
            if(!Character.isDigit(c))
                return false;
        }
        if(tourist.getLocation().isEmpty())
            return false;
        return true;
    }

}