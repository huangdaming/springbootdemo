package com.hdm.springbootdemo.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SendMailQuartz
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 18:13
 */
@Component
@Configurable
@EnableScheduling
public class SendMailQuartz {
    private static final Logger LOGGER = LogManager.getLogger(SendMailQuartz.class);

    //每五秒执行一次
    @Scheduled(cron = "*/5 * *  * * * ")
    public void sendCurrentByCron() {
        LOGGER.info("定时器运行了！！1");
    }
}
