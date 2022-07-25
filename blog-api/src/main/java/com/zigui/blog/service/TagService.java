package com.zigui.blog.service;

import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.TagVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 12:39
 * @Description:
 */

public interface TagService {
    List<TagVO> findTagsByArticleId(Long id);

    Result findHotTagVoList(int limit);
}
