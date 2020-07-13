package com.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.entity.Result;
import com.mall.entity.StatusCode;
import com.mall.mapper.UserMapper;
import com.mall.pojo.User;
import com.mall.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liujing123
 * @since 2020-07-03
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //登录
    public Result login(String username,String password){
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            User user = userMapper.selectOne(queryWrapper);
            if(user !=null){
                System.out.println(user.toString());
                if(user.getPassword().equals(password)){
                    return new Result(true,StatusCode.OK,"登陆成功！",user);
                }else {
                    return new Result(false,StatusCode.LOGINERROR,"账号或密码错误！");
                }
            }else {
                return new Result(false,StatusCode.LOGINERROR,"账号或密码错误！");
            }

        }catch (Exception e){
            System.out.println(e);
            return new Result(false,StatusCode.ERROR,"获取数据失败");

        }
    }

    //注册
    public Result register(String username,String password){
        try{
            User user = new User();
            IdWorker idWorker = new IdWorker();
            String id = idWorker.nextId()+"";
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insert(user);
            return new Result(true, StatusCode.OK,"插入成功！");
        }catch (Exception e){
            System.out.println(e);
            return new Result(false, StatusCode.ERROR,"插入失败！");

        }
    }

}
