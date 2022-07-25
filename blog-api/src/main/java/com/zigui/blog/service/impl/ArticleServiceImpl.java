package com.zigui.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zigui.blog.dao.dto.ArchivesDTO;
import com.zigui.blog.dao.mapper.ArticleBodyMapper;
import com.zigui.blog.dao.mapper.ArticleMapper;
import com.zigui.blog.dao.pojo.Article;
import com.zigui.blog.dao.pojo.ArticleBody;
import com.zigui.blog.service.ArticleService;
import com.zigui.blog.service.SysUserService;
import com.zigui.blog.service.TagService;
import com.zigui.blog.service.ThreadService;
import com.zigui.blog.vo.ArticleBodyVO;
import com.zigui.blog.vo.ArticleVO;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.PageParams;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:56
 * @Description:
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Autowired
    private ThreadService threadService;


    @Override
    public Result listArticle(PageParams pageParams) {
        /**
         * 分页查询数据库表
         */
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //置顶
        //倒序排列
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        //为什么在这不直接返回 VO(Value Object)值对象，用于业务层之间的的数据传递，或者对应于页面上需要显示的数据（表单）。
        //把pojo的对象放到vo对象中

        List<Article> records = articlePage.getRecords();
        List<ArticleVO> articleVOList = copyList(records);
        return Result.success(articleVOList);
    }

    @Override
    public Result hotArticle(int limit) {
        List<Article> hotArticles = articleMapper.findHotArticles(limit);
        List<ArticleVO> articleVOList = copyList(hotArticles);
        return Result.success(articleVOList);
    }

    @Override
    public Result newArticle(int limit){
        List<Article> newArticles = articleMapper.findNewArticles(limit);
        List<ArticleVO> articleVOList = copyList(newArticles);
        return Result.success(articleVOList);
    }

    @Override
    public Result listArchives() {
        List<ArchivesDTO> archivesDTOList = articleMapper.findArchivesDTO();
        return Result.success(archivesDTOList);
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Override
    public Result getArticleById(long id) {
        ArticleBody articleBody = articleBodyMapper.selectById(id);
        Article article = articleMapper.selectById(id);

//        System.out.println(articleBody);
        ArticleBodyVO articleBodyVO = new ArticleBodyVO();
        articleBodyVO.setContent(articleBody.getContent());
        articleBodyVO.setContentHtml(articleBody.getContentHtml());
        articleBodyVO.setArticleId(articleBody.getId());
        articleBodyVO.setId(articleBody.getId());
        ArticleVO articleVO = copy(article, true, true);
        articleVO.setBody(articleBodyVO);
        return Result.success(articleVO);
    }




    @Override
    public ArticleVO findArticleById(Long id) {
        Article article = articleMapper.selectById(id);

        return copy(article,true,true);
    }

    private List<ArticleVO> copyList(List<Article> records) {
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article record : records) {
            articleVOList.add(copy(record,true,true));
        }
        return articleVOList;
    }



    private ArticleVO copy(Article article, Boolean isTag, Boolean isAuthor) {
        ArticleVO articleVO = new ArticleVO();
        //BeanUtils.copyProperties("转换前的类", "转换后的类");
        BeanUtils.copyProperties(article, articleVO);
        if (isTag) {
            articleVO.setTags(tagService.findTagsByArticleId(article.getId()));
        }
        if (isAuthor) {
            articleVO.setAuthor(sysUserService.findSysUserById(article.getId()).getNickname());
        }
        articleVO.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        return articleVO;
    }
}
