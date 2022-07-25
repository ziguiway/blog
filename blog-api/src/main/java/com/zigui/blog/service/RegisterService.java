package com.zigui.blog.service;

import com.zigui.blog.dao.pojo.SysUser;
import com.zigui.blog.vo.Result;
import com.zigui.blog.vo.param.RegisterParams;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:32
 * @Description:
 */

public interface RegisterService {
    Result register (RegisterParams registerParams);
}
