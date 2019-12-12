package com.hdm.springbootdemo.service;

import com.hdm.springbootdemo.model.AyMood;

/**
 * @ClassName: AyMoodService
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 19:47
 */
public interface AyMoodService {
    AyMood save(AyMood ayMood);
    String asynSave(AyMood ayMood) ;
}
