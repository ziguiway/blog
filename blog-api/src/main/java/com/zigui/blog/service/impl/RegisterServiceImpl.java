package com.zigui.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zigui.blog.dao.pojo.SysUser;
import com.zigui.blog.service.RegisterService;
import com.zigui.blog.service.SysUserService;
import com.zigui.blog.utils.JWTUtils;
import com.zigui.blog.vo.ErrorCode;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.RegisterParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:32
 * @Description:
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    private static final String salt = "ziguiway!@#";

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;




    @Override
    public Result register(RegisterParams registerParams) {
        String account = registerParams.getAccount();
        String password = registerParams.getPassword();
        String nickName = registerParams.getNickname();
        System.out.println(account+" "+password+" "+nickName);
        if (StringUtils.isEmpty(account)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickName)){
            return Result.failure(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = new SysUser();
        sysUser.setNickname(nickName);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+salt));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        Boolean flag = sysUserService.checkSysUserUnique(account);
        if (flag) {
            return Result.failure(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        sysUserService.addSysUser(sysUser);
        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
