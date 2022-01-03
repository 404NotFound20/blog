package com.ender;

import com.ender.util.JwtUtils;
import com.ender.util.SmsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    SmsUtils smsUtils;
    @Test
    void test() {
        System.out.println(smsUtils.getSdkAppId());
        smsUtils.sendSms();
    }

}
