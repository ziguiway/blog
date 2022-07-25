package com.zigui.blog.vo.param;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 11:34
 * @Description:
 */

@Data
public class RegisterParams {
    private String account;
    private String password;
    private String nickname;
}
