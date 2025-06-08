package com.zd.tourism_system_2025_v1.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Article {

    private Integer articleID;
    private Integer userID;
    private String title;
    private String content;
    private Tourist author;
    private ArticleState state;
    private LocalDateTime createTime;

    // 构造函数
    public Article() {
        this.state = new DraftState();  // 默认状态为草稿
        this.createTime = LocalDateTime.now();
    }

    // 提交审核
    public boolean submitForReview() {
        if (state.canSubmit()) {
            //state = state.getNextState();  // 状态转换为待审核
            state = new PendingState();
            return true;
        }
        return false;
    }

    // 编辑文章
    public boolean edit(){
    //public boolean edit(String newTitle, String newContent) {
        if (state.canEdit()) {
            //this.title = newTitle;
            //this.content = newContent;
            return true;
        }
        return false;
    }

    // 删除文章
    public boolean delete() {
        return state.canDelete();
    }

    // 发布文章
    public boolean publish() {
        if (state.canPublish()) {
            state = new PublishedState();
            return true;
        }
        return false;
    }
    //拒绝文章
    public boolean reject() {
        if (state.canReject()) {
            state = new RejectState();
            return true;
        }
        return false;
    }

    // 获取当前状态描述
    /*public String getStateDescription() {
        return state.getStateDescription();
    }

    /*
    // Getters and Setters
    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state.getState();
    }

    public void setState(ArticleState state) {
        this.state = state;
    }

     */
}

