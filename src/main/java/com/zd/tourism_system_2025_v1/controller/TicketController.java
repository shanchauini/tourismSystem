package com.zd.tourism_system_2025_v1.controller;

import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.model.Ticket;
import com.zd.tourism_system_2025_v1.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //添加新门票
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTicket(@RequestBody Ticket ticket) {
        try {
            boolean isSuccess = ticketService.addTicket(ticket);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "门票添加成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "门票添加失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //更新门票信息
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateTicket(@RequestBody Ticket ticket) {
        try {
            boolean isSuccess = ticketService.updateTicket(ticket);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "门票信息更新成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "门票信息更新失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //删除门票
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteTicket(@RequestParam int ticketID) {
        try {
            boolean isSuccess = ticketService.deleteTicket(ticketID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "门票删除成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "门票删除失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取门票详情
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getTicketById(@RequestParam int ticketID) {
        try {
            Ticket ticket = ticketService.getTicketById(ticketID);
            if (ticket != null) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "获取门票详情成功", ticket)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(false, "未找到该门票", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取景点的所有门票
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getTicketsByAttractionId(@RequestParam int attractionID) {
        try {
            List<Ticket> tickets = ticketService.getTicketsByAttractionId(attractionID);
            return ResponseEntity.ok(
                new ApiResponse(true, "获取景点门票列表成功", tickets)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //更新门票库存
    @PostMapping("/update_stock")
    public ResponseEntity<ApiResponse> updateTicketStock(
            @RequestParam int ticketID,
            @RequestParam int newStock) {
        try {
            boolean isSuccess = ticketService.updateTicketStock(ticketID, newStock);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "门票库存更新成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "门票库存更新失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }
} 