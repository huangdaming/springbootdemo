package com.hdm.springbootdemo.service.impl;

import com.hdm.springbootdemo.dao.AyUserDao;
import com.hdm.springbootdemo.model.AyUser;
import com.hdm.springbootdemo.repository.AyUserRepository;
import com.hdm.springbootdemo.service.AyUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.hdm.springbootdemo.constant.ConfigConstant.ALL_USER;

/**
 * @ClassName: AyUserServiceImpl
 * @description:
 * @author: huangdaming
 * @Date: 2019-11-27 21:54
 */
@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource
    AyUserRepository ayUserRepository;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    AyUserDao ayUserDao;

    @Override
    public AyUser findById(String id) {
        //1、查询Redis缓存中的所有数据
        List<AyUser> allUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (allUserList != null && allUserList.size() > 0) {
            for (AyUser ayUser : allUserList) {
                if (ayUser.getId().equals(id)) {
                    return ayUser;
                }
            }
        }
        //2、查询数据库中的数据
        AyUser user = ayUserRepository.getOne(id);
        if (user != null) {
            //将查到的用户存到缓存中
            redisTemplate.opsForList().leftPush(ALL_USER, user);
        }

        return user;
    }

    @Override
    public List<AyUser> findAll() {
        return ayUserRepository.findAll();
    }

    @Override
    @Transactional
    public AyUser save(AyUser ayUser) {
        AyUser saveUser = ayUserRepository.save(ayUser);
        //出现空指针异常
        String error = null;
        error.split(",");
        return saveUser;
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name,password);
    }
}
