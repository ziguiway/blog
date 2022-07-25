package com.zigui.blog.controller;

import com.zigui.blog.service.TagService;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 15:36
 * @Description:
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/hot")
    public Result listHotTags(){
        int limit = 5;
        return tagService.findHotTagVoList(limit);
    }

}
