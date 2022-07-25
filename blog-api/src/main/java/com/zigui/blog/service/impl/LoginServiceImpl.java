package com.zigui.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.zigui.blog.dao.pojo.SysUser;
import com.zigui.blog.service.LoginService;
import com.zigui.blog.service.SysUserService;
import com.zigui.blog.utils.JWTUtils;
import com.zigui.blog.vo.ErrorCode;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.LoginParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/20
 * @Time: 11:19
 * @Description:
 */

@Service
public class LoginServiceImpl implements LoginService {

    private static final String salt = "ziguiway!@#";

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 登录
     *
     * @param loginParams
     * @return
     */
    @Override
    public Result login(LoginParams loginParams) {
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.failure(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + salt);
        SysUser sysUser = sysUserService.findSysUserByAccountAndPassword(account,pwd);
        if (sysUser == null){
            return Result.failure(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
