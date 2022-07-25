package com.zigui.blog.controller;

import com.zigui.blog.service.ArticleService;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:44
 * @Description:
 */
//json进行交互
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 首页 文章列表
     *
     * @param pageParams
     * @return
     */
    @PostMapping
    Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    /**
     * 最热文章
     *
     * @return
     */
    @PostMapping("/hot")
    Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 最新文章
     *
     * @return
     */
    @PostMapping("/new")
    Result newArticle() {
        int limit = 5;
        return articleService.newArticle(limit);
    }

    /**
     * 文章归档
     *
     * @return
     */
    @PostMapping("/listArchives")
    Result listArchives() {
        return articleService.listArchives();
    }
    /**
     * 获取文章详情
     * @return
     */
    @PostMapping("/view/{id}")
    public Result getArticleById(@PathVariable("id") Long id){
        return articleService.getArticleById(id);
    }
}