package com.zigui.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zigui.blog.dao.dto.ArchivesDTO;
import com.zigui.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:40
 * @Description:
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> findHotArticles(int limit);

    List<Article> findNewArticles(int limit);

    List<ArchivesDTO> findArchivesDTO();
}
