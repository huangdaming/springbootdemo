package com.hdm.springbootdemo.service;

import com.hdm.springbootdemo.model.AyMood;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: AyMoodConsumer
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 20:02
 */
@Component
public class AyMoodConsumer {

    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text) {
        System.out.println("用户发表说说【" + text + "】成功！");
    }

    @Resource
    AyMoodService ayMoodService;

    @JmsListener(destination = "ay.queue.asy.save")
    public void receiveQueue(AyMood ayMood) {
        ayMoodService.save(ayMood);
    }
}
