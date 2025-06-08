package com.zd.tourism_system_2025_v1.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zd.tourism_system_2025_v1.controller.ArticleController;

@JsonDeserialize(using = ArticleController.ArticleStateDeserializer.class)
public interface ArticleState {

    String getState();
    
    // 提交审核
    boolean canSubmit();
    
    // 编辑文章
    boolean canEdit();
    
    // 删除文章
    boolean canDelete();
    
    // 发布文章
    boolean canPublish();

    //拒绝文章
    boolean canReject();
    
    // 获取状态描述
    String getStateDescription();
    
    // 获取下一个可能的状态
    // getNextState();
}