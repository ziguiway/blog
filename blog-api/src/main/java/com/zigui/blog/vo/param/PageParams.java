package com.zigui.blog.vo.param;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:47
 * @Description:
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
}
