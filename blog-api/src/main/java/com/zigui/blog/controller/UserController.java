package com.zigui.blog.controller;

import com.zigui.blog.service.SysUserService;
import com.zigui.blog.vo.LoginUserVO;
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
 * @Time: 10:05
 * @Description:
 */

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    SysUserService sysUserService;
    @GetMapping("/currentUser")
    public Result getCurrentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findCurrentUser(token);
    }
}
