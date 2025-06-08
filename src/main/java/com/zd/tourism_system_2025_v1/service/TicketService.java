package com.zd.tourism_system_2025_v1.service;

import com.zd.tourism_system_2025_v1.DAO.TicketDao;
import com.zd.tourism_system_2025_v1.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    //处理门票业务逻辑

    private final TicketDao ticketDao;

    @Autowired
    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    //添加新门票
    public boolean addTicket(Ticket ticket) {
        try {
            if (validateTicketInfo(ticket) == false) {
                System.out.println("Ticket Info is not valid");
                return false;
            }
            int result = ticketDao.addTicket(ticket);
            return result > 0; //判断添加是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //更新门票信息
    public boolean updateTicket(Ticket ticket) {
        try {
            if (validateTicketInfo(ticket) == false) {
                System.out.println("Ticket Info is not valid");
                return false;
            }
            int result = ticketDao.updateTicket(ticket);
            return result > 0; //判断更新是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除门票
    public boolean deleteTicket(int ticketID) {
        try {
            int result = ticketDao.deleteTicket(ticketID);
            return result > 0; //判断删除是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取门票详情
    public Ticket getTicketById(int ticketID) {
        try {
            return ticketDao.getTicketById(ticketID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取景点的所有门票
    public List<Ticket> getTicketsByAttractionId(int attractionID) {
        try {
            return ticketDao.getTicketsByAttractionId(attractionID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //更新门票库存
    public boolean updateTicketStock(int ticketID, int newStock) {
        try {
            if (newStock < 0) {
                System.out.println("Stock cannot be negative");
                return false;
            }
            int result = ticketDao.updateTicketStock(ticketID, newStock);
            return result > 0; //判断更新是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //验证门票信息
    private boolean validateTicketInfo(Ticket ticket) {
        //判断门票信息是否有效
        if (ticket.getAttractionID() <= 0) {
            return false;
        }
        if (ticket.getType() == null || ticket.getType().isEmpty()) {
            return false;
        }
        if (ticket.getPrice() <= 0) {
            return false;
        }
        if (ticket.getStock() < 0) {
            return false;
        }
        return true;
    }
} 