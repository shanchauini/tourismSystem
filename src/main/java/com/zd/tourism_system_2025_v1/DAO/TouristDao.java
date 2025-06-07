package com.zd.tourism_system_2025_v1.DAO;

import com.zd.tourism_system_2025_v1.Util.DBUtil;

import com.zd.tourism_system_2025_v1.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class TouristDao {

    private final DBUtil dbUtil;

    @Autowired
    public TouristDao(DBUtil dbutil) {
        this.dbUtil = dbutil;
    }

    //普通用户注册信息
    //自增属性不需要插入参数
    public int addTourist(Tourist tourist) throws SQLException {
        String sql = "insert into user(password,userType," +
                "nickName,gender,phone,location) values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, admin.getUserId());
            pstmt.setString(1, tourist.getPassword());
            pstmt.setString(2, "TOURIST");
            pstmt.setString(3, tourist.getNickName());
            pstmt.setString(4, tourist.getGender());
            pstmt.setString(5, tourist.getPhone());
            pstmt.setString(6, tourist.getLocation());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //根据id查询普通用户
    public Tourist getTouristByUserId(int userID) throws SQLException {
        String sql = "select * from user where userID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tourist tourist = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                tourist = new Tourist();
                tourist.setUserID(userID);
                tourist.setPassword(rs.getString("password"));
                tourist.setUserType(rs.getString("userType"));
                tourist.setNickName(rs.getString("nickName"));
                tourist.setGender(rs.getString("gender"));
                tourist.setPhone(rs.getString("phone"));
                tourist.setLocation(rs.getString("location"));
            }
            return tourist;

        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    // 用户信息更新方法
    public int updateTourist(Tourist tourist) throws SQLException {
        // 更新除ID外的所有属性
        String sql = "UPDATE user SET " +
                "password = ?, " +
                "userType = ?, " +
                "nickName = ?, " +
                "gender = ?, " +
                "phone = ?, " +
                "location = ? " +
                "WHERE userID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            // 设置更新参数
            pstmt.setString(1, tourist.getPassword());
            pstmt.setString(2, tourist.getUserType());
            pstmt.setString(3, tourist.getNickName());
            pstmt.setString(4, tourist.getGender());
            pstmt.setString(5, tourist.getPhone());
            pstmt.setString(6, tourist.getLocation());

            // 设置WHERE条件参数
            pstmt.setInt(7, tourist.getUserID());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

}