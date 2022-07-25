package com.zigui.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zigui.blog.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhengshuang
 * @Date: 2022/7/18
 * @Time: 22:42
 * @Description:
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findSysUserByAccountAndPassword(String account, String password);

    Boolean addUser(String username,String password,String nickname);

    Boolean findSysUserByAccount(String account);


}
