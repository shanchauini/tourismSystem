package com.zd.tourism_system_2025_v1.model;

public  interface ArticleState {
    void handleSubmission(Article article);
    void handleApproval(Article article);
    void handleRejection(Article article);
    String getStateName();
}