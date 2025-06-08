package com.zd.tourism_system_2025_v1.model;

public class RejectState implements ArticleState{

    @Override
    public String getState() {
        return "REJECTED";
    }

    @Override
    public boolean canSubmit() {
        return true;  // 草稿状态可以提交审核
    }

    @Override
    public boolean canEdit() {
        return true;  // 草稿状态可以编辑
    }

    @Override
    public boolean canDelete() {
        return true;  // 草稿状态可以删除
    }

    @Override
    public boolean canPublish() {
        return false;  // 草稿状态不能直接发布
    }

    @Override
    public boolean canReject() {return false;}

    @Override
    public String getStateDescription() {
        return "驳回状态：文章正在编辑中，可以随时修改";
    }

    /*@Override
    public ArticleState getNextState() {
        return new PendingState();  // 草稿状态的下一个状态是待审核状态
    }
    /*
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

     */
}
