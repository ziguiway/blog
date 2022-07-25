package com.zigui.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Boolean success;
    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(true,200,"success",data);
    }

    public static Result failure(int code,String message) {
        return new Result(false,code,message,null);
    }
}
