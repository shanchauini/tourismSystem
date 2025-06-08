package com.zd.tourism_system_2025_v1.model;

public class PendingState implements ArticleState{

    @Override
    public String getState() {
        return "PENDING";
    }

    @Override
    public boolean canSubmit() {
        return false;  // 草稿状态可以提交审核
    }

    @Override
    public boolean canEdit() {
        return true;  // 草稿状态可以编辑
    }
    //申请后可以自己退回再编辑？

    @Override
    public boolean canDelete() {
        return true;  // 草稿状态可以删除
    }

    @Override
    public boolean canPublish() {
        return true;  // 草稿状态不能直接发布
    }

    @Override
    public boolean canReject(){return true;}

    @Override
    public String getStateDescription() {
        return "待审核状态：文章正在编辑中，可以随时修改";
    }

    //状态转换有问题，以及是否要要增加一个canreject
    /*@Override
    public ArticleState getNextState() {
        return new PendingState();  // 草稿状态的下一个状态是待审核状态
    }
    /*
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
     */


}
