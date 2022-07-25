package com.zigui.blog.service;

import com.zigui.blog.vo.ArticleVO;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.PageParams;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:55
 * @Description:
 */

public interface ArticleService {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    Result hotArticle(int limit);

    Result newArticle(int limit);

    Result listArchives();

    Result getArticleById(long id);

    ArticleVO findArticleById(Long id);

}
