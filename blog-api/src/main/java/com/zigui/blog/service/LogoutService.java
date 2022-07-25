package com.zigui.blog.service;

import com.zigui.blog.vo.Result;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:18
 * @Description:
 */

public interface LogoutService {
    Result logout(String token);
}
