package com.hdm.springbootdemo.service;

import com.hdm.springbootdemo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 描述：用户服务层接口
 * @author 阿毅
 * @date   2017/10/14
 */
public interface AyUserService {

    AyUser findById(String id);

    List<AyUser> findAll();

    AyUser save(AyUser ayUser);

    void delete(String id);

    //分页
    Page<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);

    AyUser findByNameAndPassword(String name,String password);
}
