package com.zd.tourism_system_2025_v1.DAO;

import com.zd.tourism_system_2025_v1.Util.DBUtil;
import com.zd.tourism_system_2025_v1.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDao {

    private final DBUtil dbUtil;

    @Autowired
    public TicketDao(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    //添加新门票
    public int addTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (attractionID, type, price, stock, description) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ticket.getAttractionID());
            pstmt.setString(2, ticket.getType());
            pstmt.setInt(3, ticket.getPrice());
            pstmt.setInt(4, ticket.getStock());
            pstmt.setString(5, ticket.getDescription());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //更新门票信息
    public int updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE ticket SET attractionID=?, type=?, price=?, stock=?, description=? WHERE ticketID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ticket.getAttractionID());
            pstmt.setString(2, ticket.getType());
            pstmt.setInt(3, ticket.getPrice());
            pstmt.setInt(4, ticket.getStock());
            pstmt.setString(5, ticket.getDescription());
            pstmt.setInt(6, ticket.getTicketID());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //删除门票
    public int deleteTicket(int ticketID) throws SQLException {
        String sql = "DELETE FROM ticket WHERE ticketID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ticketID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //根据ID查询门票
    public Ticket getTicketById(int ticketID) throws SQLException {
        String sql = "SELECT * FROM ticket WHERE ticketID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Ticket ticket = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ticketID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketID(rs.getInt("ticketID"));
                ticket.setAttractionID(rs.getInt("attractionID"));
                ticket.setType(rs.getString("type"));
                ticket.setPrice(rs.getInt("price"));
                ticket.setStock(rs.getInt("stock"));
                ticket.setDescription(rs.getString("description"));
            }
            return ticket;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //根据景点ID查询门票
    public List<Ticket> getTicketsByAttractionId(int attractionID) throws SQLException {
        String sql = "SELECT * FROM ticket WHERE attractionID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, attractionID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketID(rs.getInt("ticketID"));
                ticket.setAttractionID(rs.getInt("attractionID"));
                ticket.setType(rs.getString("type"));
                ticket.setPrice(rs.getInt("price"));
                ticket.setStock(rs.getInt("stock"));
                ticket.setDescription(rs.getString("description"));
                tickets.add(ticket);
            }
            return tickets;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //更新门票库存
    public int updateTicketStock(int ticketID, int newStock) throws SQLException {
        String sql = "UPDATE ticket SET stock=? WHERE ticketID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newStock);
            pstmt.setInt(2, ticketID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }
} 