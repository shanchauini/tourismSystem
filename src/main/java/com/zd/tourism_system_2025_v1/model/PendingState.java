package com.zd.tourism_system_2025_v1.model;

public class PendingState implements ArticleState{

    @Override
    public void handleSubmission(Article article) {
        System.out.println("待审核文章不可以重复提交");
    }

    @Override
    public void handleApproval(Article article) {
        article.setState(new PublishedState());
        System.out.println("文章已通过");
    }
    @Override
    public void handleRejection(Article article) {
        //处理拒绝
        article.setState(new RejectState());
        System.out.println("文章已驳回");
    }

    @Override
    public String getStateName() {
        return "待审核";
    }
}
