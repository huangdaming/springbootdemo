package com.hdm.springbootdemo.controller;

import com.hdm.springbootdemo.config.BusinessException;
import com.hdm.springbootdemo.model.AyUser;
import com.hdm.springbootdemo.service.AyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: AyUserController
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 10:38
 */
@Controller
@RequestMapping("/ayUser")
public class AyUserController {

    @Resource
    private AyUserService ayUserService;

    @RequestMapping("/test")
    public String test(Model model) {
        //查询数据库所有的用户
        List<AyUser> ayUsers = ayUserService.findAll();
        model.addAttribute("users",ayUsers);
        return "ayUser";
    }

    @RequestMapping("/findAll")
    public String findAll(Model modle) {
        throw new BusinessException("业务异常");
    }
}
