package com.zigui.blog.service;

import com.zigui.blog.dao.pojo.SysUser;
import com.zigui.blog.vo.LoginUserVO;
import com.zigui.blog.vo.Result;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/19
 * @Time: 14:40
 * @Description:
 */

public interface SysUserService {
    SysUser findSysUserById(Long id);

    SysUser findSysUserByAccountAndPassword(String account, String password);

    Result findCurrentUser(String token);

    Boolean addSysUser(SysUser sysUser);

    Boolean checkSysUserUnique(String account);


}
