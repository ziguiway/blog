package com.zigui.blog.service;

import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.LoginParams;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/20
 * @Time: 11:17
 * @Description:
 */

public interface LoginService {
    Result login(LoginParams loginParams);
}
