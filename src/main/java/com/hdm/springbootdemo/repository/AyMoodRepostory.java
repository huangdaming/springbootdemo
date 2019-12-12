package com.hdm.springbootdemo.repository;

import com.hdm.springbootdemo.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: AyMoodRepostory
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 19:33
 */
public interface AyMoodRepostory extends JpaRepository<AyMood,String> {
}
