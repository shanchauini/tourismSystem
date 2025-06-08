package com.zd.tourism_system_2025_v1.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zd.tourism_system_2025_v1.model.*;
import com.zd.tourism_system_2025_v1.response.ApiResponse;
import com.zd.tourism_system_2025_v1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //创建新文章
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createArticle(@RequestBody Article article) {
        try {
            int articleId = articleService.createArticle(article);
            if (articleId > 0) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章创建成功", articleId)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "文章创建失败", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //更新文章
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateArticle(@RequestBody Article article) {
        try {
            boolean isSuccess = articleService.updateArticle(article);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章更新成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "当前状态不允许编辑文章", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //删除文章
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteArticle(@RequestParam int articleID) {
        try {
            boolean isSuccess = articleService.deleteArticle(articleID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章删除成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "当前状态不允许删除文章", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取文章详情
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getArticleById(@RequestParam int articleID) {
        try {
            Article article = articleService.getArticleById(articleID);
            if (article != null) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "获取文章详情成功", article)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(false, "未找到该文章", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取用户的所有文章
    @GetMapping("/user-articles")
    public ResponseEntity<ApiResponse> getArticlesByUserId(@RequestParam int userID) {
        try {
            List<Article> articles = articleService.getArticlesByUserId(userID);
            return ResponseEntity.ok(
                new ApiResponse(true, "获取用户文章列表成功", articles)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取所有已发布的文章
    @GetMapping("/published")
    public ResponseEntity<ApiResponse> getPublishedArticles() {
        try {
            List<Article> articles = articleService.getPublishedArticles();
            return ResponseEntity.ok(
                new ApiResponse(true, "获取已发布文章列表成功", articles)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //获取待审核的文章（管理员接口）
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse> getPendingArticles() {
        try {
            List<Article> articles = articleService.getPendingArticles();
            return ResponseEntity.ok(
                new ApiResponse(true, "获取待审核文章列表成功", articles)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //提交文章审核
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submitForReview(@RequestParam int articleID) {
        try {
            boolean isSuccess = articleService.submitForReview(articleID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章提交审核成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "当前状态不允许提交审核", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //审核通过文章（管理员接口）
    @PostMapping("/approve")
    public ResponseEntity<ApiResponse> approveArticle(@RequestParam int articleID) {
        try {
            boolean isSuccess = articleService.approveArticle(articleID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章审核通过成功", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "当前状态不允许审核通过", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    //拒绝文章（管理员接口）
    @PostMapping("/reject")
    public ResponseEntity<ApiResponse> rejectArticle(@RequestParam int articleID) {
        try {
            boolean isSuccess = articleService.rejectArticle(articleID);
            if (isSuccess) {
                return ResponseEntity.ok(
                    new ApiResponse(true, "文章已拒绝", null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "当前状态不允许拒绝", null)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(false, "服务器错误: " + e.getMessage(), null)
            );
        }
    }

    public class ArticleStateDeserializer extends JsonDeserializer<ArticleState> {
        @Override
        public ArticleState deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException {
            String value = p.getText();
            // 根据字符串返回对应实现类
            return switch(value.toUpperCase()) {
                case "PUBLISHED" -> new PublishedState();
                case "DRAFT" -> new DraftState();
                case "PENDING" -> new PendingState();
                case "REJECTED" -> new RejectState();
                default -> throw new IllegalArgumentException("无效状态");

            };
        }
    }

} 