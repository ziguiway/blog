package com.zigui.blog.controller;

import com.zigui.blog.service.RegisterService;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.RegisterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:31
 * @Description:
 */

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody RegisterParams registerParams){
        return registerService.register(registerParams);
    }
}
