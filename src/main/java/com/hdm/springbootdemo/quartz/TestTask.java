package com.hdm.springbootdemo.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: TestTask
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 18:10
 */
public class TestTask {
    private static final Logger LOGGER = LogManager.getLogger(TestTask.class);

    public void run() {
        LOGGER.info("定时器运行了！！");
    }
}
