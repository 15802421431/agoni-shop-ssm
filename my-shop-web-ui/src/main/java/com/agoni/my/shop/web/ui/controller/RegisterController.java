package com.agoni.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注册控制器
 * <p>Title: RegisterController</p>
 * <p>Description: </p>
 *
 * @author Soulmate
 * @version 1.0.0
 * @date 2019/9/4 12:34
 */
@Controller
public class RegisterController {

    /**
     * 跳转注册页
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
}
