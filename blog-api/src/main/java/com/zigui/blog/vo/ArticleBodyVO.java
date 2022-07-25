package com.zigui.blog.vo;

import lombok.Data;

@Data
public class ArticleBodyVO {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}