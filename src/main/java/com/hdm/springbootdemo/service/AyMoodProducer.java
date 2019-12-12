package com.hdm.springbootdemo.service;

import com.hdm.springbootdemo.model.AyMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @ClassName: AyMoodProducer
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 19:55
 */
@Component
public class AyMoodProducer {
    @Resource
    JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }

    public void sendMessage(Destination destination, final AyMood ayMood) {
        jmsMessagingTemplate.convertAndSend(destination, ayMood);
    }
}
