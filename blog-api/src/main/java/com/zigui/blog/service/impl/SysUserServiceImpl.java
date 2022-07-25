package com.zigui.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zigui.blog.dao.mapper.SysUserMapper;
import com.zigui.blog.dao.pojo.SysUser;
import com.zigui.blog.service.SysUserService;
import com.zigui.blog.utils.JWTUtils;
import com.zigui.blog.vo.ErrorCode;
import com.zigui.blog.vo.LoginUserVO;
import com.zigui.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 14:41
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public SysUser findSysUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setNickname("ziguiway");
        }
        return sysUser;
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @Override
    public SysUser findSysUserByAccountAndPassword(String account, String password) {
        SysUser sysUser = sysUserMapper.findSysUserByAccountAndPassword(account, password);
        return sysUser;
    }

    /**
     * 获取当前登录用户
     * @param token
     * @return
     */
    @Override
    public Result findCurrentUser(String token) {

        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map==null){
            return Result.failure(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return Result.failure(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        LoginUserVO loginUserVo = new LoginUserVO();
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        return Result.success(loginUserVo);
    }


    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @Override
    public Boolean addSysUser(SysUser sysUser) {
        int insert = this.sysUserMapper.insert(sysUser);
        return insert > 0;
    }

    /**
     * 判断用户是否已存在
     * @param account
     * @return
     */
    @Override
    public Boolean checkSysUserUnique(String account) {
        return sysUserMapper.findSysUserByAccount(account);
    }
}
