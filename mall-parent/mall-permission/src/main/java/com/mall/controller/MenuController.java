package com.mall.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.api.R;
import com.mall.entity.Result;
import com.mall.entity.StatusCode;
import com.mall.pojo.Menu;
import com.mall.pojo.User;
import com.mall.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")

public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/setMenu",method = RequestMethod.POST)
    public Result setMenu(String menuName,String menuid,String pid,String level,String icon,String showmenu,String sort,String path){
        if (pid.equals("")&&pid==null){
            pid=null;
        }
        return menuService.setMenu(menuName,menuid,pid,level,icon,showmenu,sort,path);
    }

    //根据人员角色id获取菜单列表
    @RequestMapping(value = "/getMenuListByRole",method = RequestMethod.POST)
    public Result getMenuListByRole(String id){
        String role_id = menuService.getRolename(id);
        JSONArray jsonArray = menuService.getMenuListByRole(role_id,"0");
        return new Result(true, StatusCode.OK,"获取菜单成功！",jsonArray);
    }
    //获取所有菜单列表
    @RequestMapping(value = "/getMenuList",method = RequestMethod.POST)
    public Result getMenuList(){
        return menuService.getMenuList();
    }
    //保存菜单信息
    @RequestMapping(value = "/saveMenuInfo",method = RequestMethod.POST)
    public Result saveMenuInfo(String name,String parent,String level,String path,String icon,String show,String sort){
        String pid;
        if(parent.equals("undefined")){
            pid = "0";
        }else {
            pid = parent;
        }
        return menuService.saveMenuInfo(name,pid,level,path,icon,show,sort);
    }
    //更新菜单信息
    @RequestMapping(value = "/updateMenuInfo",method = RequestMethod.POST)
    public Result updateMenuInfo(String id,String menuid,String name,String parent,String level,String path,String icon,String show,String sort){
        String pid;
        if(parent.equals("undefined")){
            pid = "0";
        }else {
            pid = parent;
        }
        return menuService.updateMenuInfo(id,menuid,name,pid,level,path,icon,show,sort);
    }

    //根据条件筛选菜单
    @RequestMapping(value = "/getMenuListByCondition",method = RequestMethod.POST)
    public Result getMenuListByCondition(String name,String level,String show,String parent){
        String pidList;
        if (parent.equals("")){
            pidList = "";
        }else {
            String pidString = menuService.getPidList(parent);
            if (pidString.equals("")){
                pidList = null;
            }else {
                pidList = pidString;
            }
        }
        return menuService.getMenuListByCondition(name,level,show,pidList);

    }

}
