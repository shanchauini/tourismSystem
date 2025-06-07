package com.zd.tourism_system_2025_v1.controller;

import com.zd.tourism_system_2025_v1.model.Attraction;
import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attraction")
public class AttractionController {
    
    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    // 添加景点接口
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAttraction(@RequestBody Attraction attraction) {
        try {
            boolean isSuccess = attractionService.addAttraction(attraction);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "景点添加成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "景点添加失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    // 更新景点信息接口
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateAttraction(@RequestBody Attraction attraction) {
        try {
            boolean isSuccess = attractionService.updateAttraction(attraction);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "景点信息更新成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "景点信息更新失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    // 删除景点接口
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteAttraction(@RequestParam  int attractionID) {
        try {
            boolean isSuccess = attractionService.deleteAttraction(attractionID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "景点删除成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "景点删除失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    // 获取景点详情接口
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getAttractionById(@RequestParam int attractionID) {
        try {
            Attraction attraction = attractionService.getAttractionById(attractionID);
            if (attraction != null) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "获取景点详情成功", attraction)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(false, "未找到该景点", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    // 获取所有景点接口
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAllAttractions() {
        try {
            List<Attraction> attractions = attractionService.getAllAttractions();
            return ResponseEntity.ok(
                new ApiResponse(true, "获取景点列表成功", attractions)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    // 搜索景点接口
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchAttractions(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String level) {
        try {
            List<Attraction> attractions = attractionService.searchAttractions(name, address, level);
            return ResponseEntity.ok(
                new ApiResponse(true, "搜索景点成功", attractions)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }
} 