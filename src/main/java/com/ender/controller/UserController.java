package com.ender.controller;


import com.ender.common.lang.Result;
import com.ender.entity.User;
import com.ender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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

    @GetMapping("/index")
    public Object index(){
        User user=userService.getById(1L);

        return Result.succ(user);
    }

}
