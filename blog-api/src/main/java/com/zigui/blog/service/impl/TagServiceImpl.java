package com.zigui.blog.service.impl;

import com.zigui.blog.dao.mapper.TagMapper;
import com.zigui.blog.dao.pojo.Article;
import com.zigui.blog.dao.pojo.Tag;
import com.zigui.blog.service.TagService;
import com.zigui.blog.vo.ArticleVO;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 12:39
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;



    @Override
    public List<TagVO> findTagsByArticleId(Long id) {
        List<Tag> tags = tagMapper.findTagsByArticleId(id);
        return copyList(tags);
    }

    @Override
    public Result findHotTagVoList(int limit) {
        List<Long> hotTagIdList = tagMapper.findHotTagIdList(limit);
        List<Tag> hotTags = tagMapper.findHotTagBysTagId(hotTagIdList);
        return Result.success(copyList(hotTags));
    }


    private List<TagVO> copyList(List<Tag> tagList) {
        List<TagVO> tagVOList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVOList.add(copy(tag));
        }
        return tagVOList;
    }


    public TagVO copy(Tag tag){
        TagVO tagVo = new TagVO();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }




}
