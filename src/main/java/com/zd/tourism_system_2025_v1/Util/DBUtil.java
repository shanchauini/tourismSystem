package com.zd.tourism_system_2025_v1.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component//这个注解是？
public class DBUtil {
    //编写数据库连接工具
    private final  DataSource datasource;

    @Autowired//这个注解呢？
    public DBUtil(DataSource datasource) {
        this.datasource = datasource;
    }

    public  Connection getConnection() throws SQLException{
        return this.datasource.getConnection();
    }

    public  void close(Connection conn, Statement stmt, ResultSet rs){
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

