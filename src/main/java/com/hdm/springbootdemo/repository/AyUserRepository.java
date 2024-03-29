package com.hdm.springbootdemo.repository;

import com.hdm.springbootdemo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 描述：用户Repository
 * @author Ay
 * @date   2017/10/14.
 */
public interface AyUserRepository extends JpaRepository<AyUser,String>{

    /**
     * 描述：通过名字相等查询，参数为 name
     * 相当于：select u from ay_user u where u.name = ?1
     */
    List<AyUser> findByName(String name);

    /**
     * 描述：通过名字like查询，参数为 name
     * 相当于：select u from ay_user u where u.name like ?1
     */
    List<AyUser> findByNameLike(String name);

    /**
     * 描述：通过主键id集合查询，参数为 id集合
     * 相当于：select u from ay_user u where id in(?,?,?)
     * @param ids
     */
    List<AyUser> findByIdIn(Collection<String> ids);


}
