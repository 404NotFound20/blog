package com.ender.common.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
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
    @Scheduled(cron = "0 */2 * * * ?")
    public void dailySms(){
        log.info("今天的短信是：「」  发送时间{}", DateUtil.now());
    }
}
