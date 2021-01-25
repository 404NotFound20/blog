package com.ender.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ender.common.dto.LoginDto;
import com.ender.common.lang.Result;
import com.ender.entity.User;
import com.ender.service.UserService;
import com.ender.util.JwtUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 账户控制器
 */
@Slf4j
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        //mybatis-plus 的应用方法
        log.info("查询数据库获取用户信息");
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        //密码加密校验
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误");
        }
        log.info("密码校验成功，生成JWT");
        String jwt = jwtUtils.generateToken(user.getId());
        log.info("在接口返回的http请求中添加jwt信息 jwt:[{}]",jwt);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        //给前端进行用户信息回显用，也可以返回null 反正有用的jwt信息在header里带过去了
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        log.info("直接使用shiro进行退出，此处的前提是先进行登录，所以要加RequiresAuthentication注解");
        SecurityUtils.getSubject().logout();
        return Result.succ(null);

    }

}
