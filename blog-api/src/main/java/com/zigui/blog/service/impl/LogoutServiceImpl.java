package com.zigui.blog.service.impl;

import com.zigui.blog.service.LogoutService;
import com.zigui.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:18
 * @Description:
 */

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success("成功注销账号");
    }
}
