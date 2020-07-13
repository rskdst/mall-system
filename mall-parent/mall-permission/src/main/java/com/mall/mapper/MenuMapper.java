package com.mall.mapper;

import com.mall.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liujing
 * @since 2020-07-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //根据rolename获取所有所有可用一级菜单
    @Select("select * from permission_menu where showmenu='1' and pid=#{pid} and id in (select menu_id from permission_menu_role where role_id = #{role_id}) order by sort")
    List<Menu> getMenuByRoleid(String role_id, String pid);

    //保存菜单
    @Select("insert into permission_menu(id,menuName,pid,level,icon,showmenu,sort,path) values(#{id},#{name},#{pid},#{level},#{icon},#{showMenu},#{sort},#{path})")
    void saveMenu(String id,String name,String pid,String level,String path,String icon,String showMenu,String sort);

    //更新菜单
    @Select("update permission_menu set id=#{id},menuName=#{name},menuid=#{menuid},pid=#{pid},level=#{level},path=#{path},icon=#{icon},showmenu=#{showMenu},sort=#{sort} where id=#{id}")
    void updateMenu(String id,String name,String menuid,String pid,String level,String path,String icon,String showMenu,String sort);

    //根据条件获取菜单列表
    @Select("select * from permission_menu where (menuName=#{name} or #{name} = '') and (level=#{level} or #{level} = '') and (showmenu=#{show} or #{show}='') and (FIND_IN_SET(pid,#{pidList}) or #{pidList}='')")
    List<Menu> getMenuListByCondition(String name,String level,String show,String pidList);

    //根据上级菜单名称获取菜单pidList
    @Select("select * from permission_menu where (menuName=#{pname} or #{pname}='')")
    List<Menu> getPidList(String pname);
}
