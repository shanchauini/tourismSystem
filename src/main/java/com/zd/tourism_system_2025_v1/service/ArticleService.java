package com.zd.tourism_system_2025_v1.service;

import com.zd.tourism_system_2025_v1.DAO.ArticleDao;
import com.zd.tourism_system_2025_v1.model.*;
import com.zd.tourism_system_2025_v1.model.RejectState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    //创建新文章（草稿状态）
    public int createArticle(Article article) {
        try {
            // 文章创建时已经是草稿状态，不需要额外设置
            return articleDao.addArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //更新文章
    public boolean updateArticle(Article article) {
        try {
            // 检查是否可以编辑
            //if (!article.edit(article.getTitle(), article.getContent()))
            if (!article.edit())
            {
                return false;
            }
            return articleDao.updateArticle(article) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除文章
    public boolean deleteArticle(int articleID) {
        try {
            Article article = articleDao.getArticleById(articleID);
            if (article == null || !article.delete()) {
                return false;
            }
            return articleDao.deleteArticle(articleID) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取文章详情
    public Article getArticleById(int articleID) {
        try {
            return articleDao.getArticleById(articleID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取用户的所有文章
    public List<Article> getArticlesByUserId(int userID) {
        try {
            return articleDao.getArticlesByUserId(userID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //获取所有已发布的文章
    public List<Article> getPublishedArticles() {
        try {
            return articleDao.getPublishedArticles();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //获取待审核的文章
    public List<Article> getPendingArticles() {
        try {
            return articleDao.getPendingArticles();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //提交文章审核
    public boolean submitForReview(int articleID) {
        try {
            Article article = articleDao.getArticleById(articleID);
            if (article == null || !article.submitForReview()) {
                return false;
            }
            return articleDao.updateArticle(article) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //审核通过文章
    public boolean approveArticle(int articleID) {
        try {
            Article article = articleDao.getArticleById(articleID);
            if (article == null || !article.publish()) {
                return false;
            }
            return articleDao.updateArticle(article) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //拒绝文章
    public boolean rejectArticle(int articleID) {
        try {
            Article article = articleDao.getArticleById(articleID);
            if (article == null||!article.reject()) {
                return false;
            }
            // 设置状态为已拒绝
            article.setState(new RejectState());
            return articleDao.updateArticle(article) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
} 