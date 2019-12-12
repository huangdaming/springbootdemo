package com.hdm.springbootdemo.service.impl;

import com.hdm.springbootdemo.model.AyMood;
import com.hdm.springbootdemo.repository.AyMoodRepostory;
import com.hdm.springbootdemo.service.AyMoodProducer;
import com.hdm.springbootdemo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @ClassName: AyMoodServiceImpl
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 19:47
 */
@Service
@Transactional
public class AyMoodServiceImpl implements AyMoodService{

    @Resource
    AyMoodRepostory ayMoodRepostory;

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepostory.save(ayMood);
    }

    //队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asy.save");

    @Resource
    AyMoodProducer ayMoodProducer;

    @Override
    public String asynSave(AyMood ayMood) {
        //往队列 ay.queue.asyn.save 推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination,ayMood) ;
        return "success";
    }
}
