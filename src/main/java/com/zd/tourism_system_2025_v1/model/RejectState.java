package com.zd.tourism_system_2025_v1.model;

public class RejectState implements ArticleState{
    @Override
    public void handleSubmission(Article article) {
        article.setState(new PublishedState());
        System.out.println("文章已提交审核");
    }

    @Override
    public void handleApproval(Article article) {
        System.out.println("驳回后不能发布");
    }

    @Override
    public void handleRejection(Article article) {
        //处理拒绝
        System.out.println("驳回后不能重复驳回");
    }
    @Override
    public String getStateName() {
        return "已驳回";
    }
}
