package com.zd.tourism_system_2025_v1.model;

public class PublishedState implements ArticleState {
    @Override
    public void handleSubmission(Article article) {
        System.out.println("已发布文章不能重复提交");
    }

    @Override
    public void handleApproval(Article article) {
        System.out.println("文章已处于发布状态");
    }

    @Override
    public void handleRejection(Article article) {
        //处理拒绝
        System.out.println("已发布文章不能驳回");
    }
    @Override
    public String getStateName() {
        return "已发布";
    }
}