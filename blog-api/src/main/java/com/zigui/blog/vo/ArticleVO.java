package com.zigui.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 9:39
 * @Description:
 */
@Data
public class ArticleVO {
    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVO body;

    private List<TagVO> tags;

    private List<CategoryVo> categorys;
}
