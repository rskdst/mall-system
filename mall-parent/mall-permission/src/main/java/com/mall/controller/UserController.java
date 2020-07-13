package com.mall.controller;


import com.mall.entity.Result;
import com.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liujing123
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/permission")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username,String password){
        return userService.login(username,password);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(String username,String password){
        return userService.register(username,password);
    }


}

