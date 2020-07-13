package com.mall.controller;


import com.mall.entity.Result;
import com.mall.mapper.RoleMapper;
import com.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujing
 * @since 2020-07-04
 */
@Controller
@RequestMapping("/permission")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //获取所有角色列表
    @RequestMapping(value = "/getAllRole",method = RequestMethod.POST)
    public Result getAllRole(){
        return roleService.getAllRole();
    }
}

