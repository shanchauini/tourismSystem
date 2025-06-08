package com.zd.tourism_system_2025_v1.DAO;

import com.zd.tourism_system_2025_v1.Util.DBUtil;
import com.zd.tourism_system_2025_v1.model.TicketOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderDao {

    private final DBUtil dbUtil;

    @Autowired
    public TicketOrderDao(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    //添加新订单
    public int addTicketOrder(TicketOrder order) throws SQLException {
        String sql = "INSERT INTO ticketOrder (userID, ticketID, attractionID, orderType, totalAmount, status, customerName, customerPhone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getUserID());
            pstmt.setInt(2, order.getTicketID());
            pstmt.setInt(3, order.getAttractionID());
            pstmt.setString(4, order.getOrderType());
            pstmt.setInt(5, order.getTotalAmount());
            pstmt.setString(6, order.getStatus());
            pstmt.setString(7, order.getCustomerName());
            pstmt.setString(8, order.getCustomerPhone());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1;
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //更新订单状态
    public int updateOrderStatus(int orderID, String status) throws SQLException {
        String sql = "UPDATE ticketOrder SET status = ? WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //获取订单详情
    public TicketOrder getOrderById(int orderID) throws SQLException {
        String sql = "SELECT * FROM ticketOrder WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToOrder(rs);
            }
            return null;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //获取用户的所有订单
    public List<TicketOrder> getOrdersByUserId(int userID) throws SQLException {
        String sql = "SELECT * FROM ticketOrder WHERE userID = ? ORDER BY createTime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TicketOrder> orders = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
            return orders;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //获取景点的所有订单
    public List<TicketOrder> getOrdersByAttractionId(int attractionID) throws SQLException {
        String sql = "SELECT * FROM ticketOrder WHERE attractionID = ? ORDER BY createTime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TicketOrder> orders = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, attractionID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
            return orders;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //将ResultSet映射为TicketOrder对象
    private TicketOrder mapResultSetToOrder(ResultSet rs) throws SQLException {
        TicketOrder order = new TicketOrder();
        order.setOrderID(rs.getInt("orderID"));
        order.setUserID(rs.getInt("userID"));
        order.setTicketID(rs.getInt("ticketID"));
        order.setAttractionID(rs.getInt("attractionID"));
        order.setOrderType(rs.getString("orderType"));
        order.setTotalAmount(rs.getInt("totalAmount"));
        order.setStatus(rs.getString("status"));
        order.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
        order.setCustomerName(rs.getString("customerName"));
        order.setCustomerPhone(rs.getString("customerPhone"));
        return order;
    }
} 