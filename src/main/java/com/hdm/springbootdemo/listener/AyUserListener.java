package com.hdm.springbootdemo.listener;

import com.hdm.springbootdemo.model.AyUser;
import com.hdm.springbootdemo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @ClassName: AyUserlistener
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 12:37
 */
@WebListener
public class AyUserListener implements ServletContextListener {

    @Resource
    AyUserService ayUserService;

    @Resource
    RedisTemplate redisTemplate;

    public static final String ALL_USER = "ALL_USER_LIST";

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {



        //查询数据库中所有的用户
        List<AyUser> allUsers = ayUserService.findAll();
        //清除缓存中所有的用户
        redisTemplate.delete(ALL_USER);
        //将数据存到缓存中
        redisTemplate.opsForList().leftPushAll(ALL_USER,allUsers);
        //查询缓存中所有的用户
        List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        logger.info("缓存中目前的用户数：" + queryUserList.size());
        logger.info("ServletContext初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("ServletContext上下文销毁");
    }
}
