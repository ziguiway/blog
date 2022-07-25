package com.zigui.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/21
 * @Time: 10:21
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVO {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
