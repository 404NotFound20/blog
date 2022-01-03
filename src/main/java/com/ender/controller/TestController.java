package com.ender.controller;

import com.ender.common.lang.Result;
import com.ender.entity.User;
import com.ender.util.SmsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2022/1/3 4:24 下午
 * @Created by zenghui
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public void index() {
        SmsUtils smsUtils=new SmsUtils();
        System.out.println(smsUtils.getSdkAppId());
    }

}
