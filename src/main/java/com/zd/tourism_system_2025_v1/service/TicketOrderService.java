package com.zd.tourism_system_2025_v1.service;

import com.zd.tourism_system_2025_v1.DAO.TicketOrderDao;
import com.zd.tourism_system_2025_v1.model.TicketOrderFactory;
import com.zd.tourism_system_2025_v1.model.TicketOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketOrderService {

    private final TicketOrderDao ticketOrderDao;
    private final TicketService ticketService;
    private final TicketOrderFactory ticketOrderFactory;

    @Autowired
    public TicketOrderService(TicketOrderDao ticketOrderDao, 
                            TicketService ticketService,
                            TicketOrderFactory ticketOrderFactory) {
        this.ticketOrderDao = ticketOrderDao;
        this.ticketService = ticketService;
        this.ticketOrderFactory = ticketOrderFactory;
    }

    //创建新订单
    public int createOrder(TicketOrder order) {
        try {
            if (!order.validate()) {
                System.out.println("订单信息无效");
                return -1;
            }
            return ticketOrderDao.addTicketOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //创建新订单（使用工厂方法）
    public TicketOrder createNewOrder() {
        return (TicketOrder) ticketOrderFactory.createOrder();
    }

    //取消订单
    public boolean cancelOrder(int orderID) {
        try {
            TicketOrder order = ticketOrderDao.getOrderById(orderID);
            if (order == null) {
                return false;
            }
            if ("CANCELLED".equals(order.getStatus())) {
                return false;
            }
            return ticketOrderDao.updateOrderStatus(orderID, "CANCELLED") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取订单详情
    public TicketOrder getOrderById(int orderID) {
        try {
            return ticketOrderDao.getOrderById(orderID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取用户的所有订单
    public List<TicketOrder> getOrdersByUserId(int userID) {
        try {
            return ticketOrderDao.getOrdersByUserId(userID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //获取景点的所有订单
    public List<TicketOrder> getOrdersByAttractionId(int attractionID) {
        try {
            return ticketOrderDao.getOrdersByAttractionId(attractionID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
} 