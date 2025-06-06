package com.zd.tourism_system_2025_v1.model;

class DraftState implements ArticleState {
    @Override
    public void handleSubmission(Article article) {
        article.setState(new PublishedState());
        System.out.println("文章已提交审核");
    }

    @Override
    public void handleApproval(Article article) {
        throw new IllegalStateException("草稿状态不能直接审核通过");
    }
    @Override
    public void handleRejection(Article article) {
        //处理拒绝
        throw new IllegalStateException("草稿状态不能直接审核驳回");
    }

    @Override
    public String getStateName() {
        return "草稿";
    }
}