package com.zd.tourism_system_2025_v1.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Article {

    private Integer articleID;
    private String title;
    private String content;
    private Tourist author;
    private ArticleState state;
    private LocalDateTime createTime;


}

