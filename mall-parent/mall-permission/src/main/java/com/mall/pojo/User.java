package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liujing123
 * @since 2020-07-03
 */
@Data
@Accessors(chain = true)
@TableName("permission_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;
    private String username;
    private String password;
    private String role;
    private String nickname;

    private String email;

    private LocalDateTime createddate;

    private String enable;

    private Integer sortid;


}
