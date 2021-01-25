package com.ender.controller;


import com.ender.common.lang.Result;
import com.ender.entity.User;
import com.ender.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ender
 * @since 2021-01-24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //不需要登录的接口
    @GetMapping("/index")
    public Object index() {
        User user = userService.getById(1L);

        return Result.succ(user);
    }

    //需要登录的接口
    @RequiresAuthentication
    @GetMapping("/index2")
    public Object index2() {
        User user = userService.getById(1L);

        return Result.succ(user);
    }
    //实体校验
    @PostMapping("/index3")
    public Object index3(@Validated  @RequestBody  User user) {

        return Result.succ(user);
    }

}
