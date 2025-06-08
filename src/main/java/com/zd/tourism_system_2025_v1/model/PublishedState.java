package com.zd.tourism_system_2025_v1.model;

public class PublishedState implements ArticleState {

    @Override
    public String getState() {
        return "PUBLISHED";
    }

    @Override
    public boolean canSubmit() {
        return false;  // 草稿状态可以提交审核
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
        return "已发布状态：文章正在发布中，可以随时修改";
    }

    /*@Override
    public ArticleState getNextState() {
        return new DraftState();  // 草稿状态的下一个状态是待审核状态
    }
    /*
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

     */
}