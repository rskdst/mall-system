package com.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.entity.Result;
import com.mall.entity.StatusCode;
import com.mall.mapper.RoleMapper;
import com.mall.pojo.Menu;
import com.mall.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujing
 * @since 2020-07-04
 */
@Service
public class RoleService {

    private RoleMapper roleMapper;

    public Result getAllRole() {
        QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
        wrapper.eq("asd","sda");

        List<Role> roleList = roleMapper.selectList(wrapper);
        return new Result(true, StatusCode.OK,"角色列表获取成功",roleList);
    }

}
