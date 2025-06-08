package com.zd.tourism_system_2025_v1.controller;

import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.model.TicketOrder;
import com.zd.tourism_system_2025_v1.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-order")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;

    @Autowired
    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    //创建新订单
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody TicketOrder order) {
        try {
            int orderId = ticketOrderService.createOrder(order);
            if (orderId > 0) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "订单创建成功", orderId)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "订单创建失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取新订单模板
    @GetMapping("/template")
    public ResponseEntity<ApiResponse> getOrderTemplate() {
        try {
            TicketOrder order = ticketOrderService.createNewOrder();
            return ResponseEntity.ok(
                new ApiResponse(true, "获取订单模板成功", order)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //取消订单
    @PostMapping("/cancel")
    public ResponseEntity<ApiResponse> cancelOrder(@RequestParam int orderID) {
        try {
            boolean isSuccess = ticketOrderService.cancelOrder(orderID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "订单取消成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "订单取消失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取订单详情
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getOrderById(@RequestParam int orderID) {
        try {
            TicketOrder order = ticketOrderService.getOrderById(orderID);
            if (order != null) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "获取订单详情成功", order)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(false, "未找到该订单", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取用户的所有订单
    @GetMapping("/user-orders")
    public ResponseEntity<ApiResponse> getOrdersByUserId(@RequestParam int userID) {
        try {
            List<TicketOrder> orders = ticketOrderService.getOrdersByUserId(userID);
            return ResponseEntity.ok(
                new ApiResponse(true, "获取用户订单列表成功", orders)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取景点的所有订单
    @GetMapping("/attraction-orders")
    public ResponseEntity<ApiResponse> getOrdersByAttractionId(@RequestParam int attractionID) {
        try {
            List<TicketOrder> orders = ticketOrderService.getOrdersByAttractionId(attractionID);
            return ResponseEntity.ok(
                new ApiResponse(true, "获取景点订单列表成功", orders)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }
} 