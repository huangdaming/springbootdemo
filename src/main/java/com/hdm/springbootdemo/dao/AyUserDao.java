package com.hdm.springbootdemo.dao;

import com.hdm.springbootdemo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AyUserDao
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 18:20
 */
@Mapper
public interface AyUserDao {

    /**
     * 描述 ： 通过用户名和密码查询用户
     * @param name
     * @param password
     */
    AyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
