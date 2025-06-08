package com.zd.tourism_system_2025_v1.DAO;

import com.zd.tourism_system_2025_v1.Util.DBUtil;
import com.zd.tourism_system_2025_v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDao {

    private final DBUtil dbUtil;

    @Autowired
    public ArticleDao(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    //添加新文章
    public int addArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (userID, title, content, state) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, article.getUserID());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getState().getState());

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

    //更新文章
    public int updateArticle(Article article) throws SQLException {
        String sql = "UPDATE article SET title = ?, content = ?, state = ? WHERE articleID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setString(3, article.getState().getState());
            pstmt.setInt(4, article.getArticleID());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //删除文章
    public int deleteArticle(int articleID) throws SQLException {
        String sql = "DELETE FROM article WHERE articleID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, articleID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    //获取文章详情
    public Article getArticleById(int articleID) throws SQLException {
        String sql = "SELECT * FROM article WHERE articleID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, articleID);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                return mapResultSetToArticle(rs);
            }
            return null;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //获取用户的所有文章
    public List<Article> getArticlesByUserId(int userID) throws SQLException {
        String sql = "SELECT * FROM article WHERE userID = ? ORDER BY createTime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articles.add(mapResultSetToArticle(rs));
            }
            return articles;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //获取所有已发布的文章
    public List<Article> getPublishedArticles() throws SQLException {
        String sql = "SELECT * FROM article WHERE state = 'PUBLISHED' ORDER BY createTime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articles.add(mapResultSetToArticle(rs));
            }
            return articles;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //获取待审核的文章
    public List<Article> getPendingArticles() throws SQLException {
        String sql = "SELECT * FROM article WHERE state = 'PENDING' ORDER BY createTime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articles.add(mapResultSetToArticle(rs));
            }
            return articles;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    //将ResultSet映射为Article对象
    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setArticleID(rs.getInt("articleID"));
        article.setUserID(rs.getInt("userID"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
        //article.setState(rs.getObject("state"));
        // 根据状态字符串创建对应的状态对象

        String state = rs.getString("state");
        switch (state) {
            case "DRAFT":
                article.setState(new DraftState());
                break;
            case "PENDING":
                article.setState(new PendingState());
                break;
            case "PUBLISHED":
                article.setState(new PublishedState());
                break;
            case "REJECTED":
                article.setState(new RejectState());
                break;
            default:
                article.setState(new DraftState());
        }
        
        return article;
    }
} 