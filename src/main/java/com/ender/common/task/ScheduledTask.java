package com.ender.common.task;

import cn.hutool.core.date.DateUtil;
import com.ender.util.JwtUtils;
import com.ender.util.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname ScheduledTask
 * @Description TODO
 * @Date 2022/1/3 1:21 上午
 * @Created by zenghui
 */
@Component
@Slf4j
public class ScheduledTask {
    @Autowired
    SmsUtils smsUtils;
    @Scheduled(cron = "*/20 * * * * ?")
    public void dailySms(){
        log.info("今天的短信是：「」  发送时间{}", DateUtil.now());
    }
}
