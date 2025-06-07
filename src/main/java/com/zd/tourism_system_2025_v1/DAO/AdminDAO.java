package com.zd.tourism_system_2025_v1.DAO;


import com.zd.tourism_system_2025_v1.Util.DBUtil;
import com.zd.tourism_system_2025_v1.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminDAO {

    private final DBUtil dbUtil;

    @Autowired
    public AdminDAO(DBUtil dbutil) {
        this.dbUtil = dbutil;
    }
    //管理员注册信息
    //自增属性不需要插入参数
    public int addAdmin(Admin admin) throws SQLException {
        String sql = "insert into user(password,userType," +
                "nickName,gender,phone,location) values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, admin.getUserId());
            pstmt.setString(1, admin.getPassword());
            pstmt.setString(2, "ADMIN");
            pstmt.setString(3, null);
            pstmt.setString(4, null);
            pstmt.setString(5, null);
            pstmt.setString(6, null);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //根据id查询管理员
    public Admin getAdminByUserId(int userID) throws SQLException {
        String sql = "select * from user where userID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setUserID(userID);
                admin.setPassword(rs.getString("password"));
                admin.setUserType(rs.getString("userType"));
            }
            return admin;

        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //管理员修改密码
    public int updateAdmin(int userID,String password) throws SQLException {
        String sql = "update user set password = ? where userID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, admin.getUserId());
            pstmt.setString(1, password);
            pstmt.setInt(2,userID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

}
