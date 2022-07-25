package com.zigui.blog.controller;

import com.zigui.blog.service.LogoutService;
import com.zigui.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:16
 * @Description:
 */

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return logoutService.logout(token);
    }

}
