package com.mall.pojo;

import java.time.LocalDate;
import java.io.Serializable;

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
@TableName("permission_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String sortid;

    private String rolename;

    private String description;

    private Integer usernum;

    private LocalDate createddate;

    private String enable;


}
