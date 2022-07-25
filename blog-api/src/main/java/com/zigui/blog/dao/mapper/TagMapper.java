package com.zigui.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zigui.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:42
 * @Description:
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> findTagsByArticleId(Long id);


    List<Long> findHotTagIdList(int limit);

    List<Tag> findHotTagBysTagId(List<Long> tagIds);


}
