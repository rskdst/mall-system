package com.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.mall.entity.Result;
import com.mall.entity.StatusCode;
import com.mall.mapper.MenuMapper;
import com.mall.mapper.RoleMapper;
import com.mall.mapper.UserMapper;
import com.mall.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.pojo.Role;
import com.mall.pojo.User;
import com.mall.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liujing
 * @since 2020-07-04
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public Result setMenu(String menuName, String menuid, String pid, String level, String icon, String showmenu, String sort, String path) {
        Menu menu = new Menu();
        IdWorker idWorker = new IdWorker();
        String id = idWorker.nextId() + "";
        menu.setId(id);
        menu.setMenuName(menuName);
        menu.setMenuid(menuid);
        menu.setPid(pid);
        menu.setLevel(level);
        menu.setIcon(icon);
        menu.setShowmenu(showmenu);
        menu.setSort(sort);
        menu.setPath(path);
        menuMapper.insert(menu);
        return new Result(true, StatusCode.OK, "菜单添加成功！");
    }

    //根据用户id获取rolename
    public String getRolename(String id) {
        User user = userMapper.selectById(id);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rolename", user.getRole());
        queryWrapper.eq("enable", "1");
        return roleMapper.selectOne(queryWrapper).getId();

    }

    //根据人员角色id获取菜单列表
    public JSONArray getMenuListByRole(String role_id, String parentid) {
        //获取所有可用的父级菜单
        List<Menu> menus = menuMapper.getMenuByRoleid(role_id, parentid);
        JSONArray jsonArray = new JSONArray();
        for (Menu menu : menus) {
            JSONObject jsonObject = new JSONObject();
            String menuid = menu.getMenuid();
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", menuid);
            queryWrapper.eq("showmenu", "1");
            List<Menu> menuList = menuMapper.selectList(queryWrapper);
            jsonObject.put("id", menu.getId());
            jsonObject.put("menuname", menu.getMenuName());
            jsonObject.put("menuid", menu.getMenuid());
            jsonObject.put("pid", menu.getPid());
            jsonObject.put("level", menu.getLevel());
            jsonObject.put("icon", menu.getIcon());
            jsonObject.put("sort", menu.getSort());
            jsonObject.put("path", menu.getPath());
            jsonObject.put("component", menu.getComponent());
            jsonObject.put("name", menu.getMenutitle());

            if (menuList.size() > 0) {
                jsonObject.put("child", getMenuListByRole(role_id, menuid));
            } else {
                jsonObject.put("child", new ArrayList<>());
            }
            jsonArray.add(jsonObject);

        }
        return jsonArray;
    }

    //获取所有菜单列表
    public Result getMenuList() {
        List<Menu> menuList = menuMapper.selectList(null);
        return new Result(true, StatusCode.OK, "获取数据成功！", menuList);
    }

    //保存菜单
    public Result saveMenuInfo(String name, String pid, String level, String path, String icon, String showMenu, String sort) {
        IdWorker idWorker = new IdWorker();
        String id = idWorker.nextId() + "";
        menuMapper.saveMenu(id, name, pid, level, path, icon, showMenu, sort);
        return new Result(true, StatusCode.OK, "菜单保存成功！");

    }

    //更新菜单
    public Result updateMenuInfo(String id, String menuid, String name, String parent, String level, String path, String icon, String show, String sort) {
        menuMapper.updateMenu(id, name, menuid, parent, level, path, icon, show, sort);
        return new Result(true, StatusCode.OK, "菜单更新成功！");
    }

    //根据上级菜单名称获取菜单pidList
    public String getPidList(String pname) {
        List<Menu> mapList = menuMapper.getPidList(pname);
        String pidList = "";
        for (int i = 0; i < mapList.size(); i++) {
            String pid = mapList.get(i).getMenuid();
            if (i == mapList.size() - 1) {
                pidList = pidList.concat(pid);
            } else {
                pidList = pidList.concat(pid + ",");
            }
        }
        return pidList;
    }

    //根据条件筛选菜单
    public Result getMenuListByCondition(String name, String level, String show, String pidList) {
        List<Menu> menuList = menuMapper.getMenuListByCondition(name, level, show, pidList);
        return new Result(true, StatusCode.OK, "菜单获取成功！", menuList);
    }

}
