package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liujing
 * @since 2020-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permission_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    @TableField("menuName")
    private String menuName;  //菜单名称

    private String menuid;  //菜单id

    private String pid;   //父级菜单id

    private String level;  //菜单级别

    private String icon;  //菜单图标

    private String showmenu;  //是否显示菜单

    private String sort;  //序号

    private String path;  //路由地址

    private String component; //组件地址

    private String menutitle; //路由名称



}
