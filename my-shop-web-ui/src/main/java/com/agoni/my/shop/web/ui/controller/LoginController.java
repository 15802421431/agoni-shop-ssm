package com.agoni.my.shop.web.ui.controller;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.utils.EmailSendUtils;
import com.agoni.my.shop.web.ui.api.UsersApi;
import com.agoni.my.shop.web.ui.constant.SystemConstant;
import com.agoni.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 *
 * @author Soulmate
 * @version 1.0.0
 * @date 2019/9/3 12:34
 */
@Controller
public class LoginController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     * @param tbUser
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest httpServletRequest) throws EmailException {

        //验证码验证
        if (!checkVerification(tbUser,httpServletRequest)) {
            model.addAttribute(BaseResult.fail("验证码输入错误"));
            return "login";
        }
        TbUser user = null;
        try {
            user = UsersApi.login(tbUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录失败
        if (user == null) {
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入"));
            return "login";
        }
        //登录成功
        else {
            //将会员信息放入Session
            httpServletRequest.getSession().setAttribute(SystemConstant.SESSION_USER_KEY, user);
            emailSendUtils.send("用户登录",String.format("congratulation 【%s】 success to sign in myShop",tbUser.getUsername()),"sjs1431@163.com");
            return "redirect:/index";
        }
    }


    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout (HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "redirect:/index";
    }

    /**
     * 检查验证码
     * @param tbUser
     * @param request
     * @return
     */
    private boolean checkVerification(TbUser tbUser,HttpServletRequest request) {
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification,tbUser.getVerification())) {
            return true;
        }else{
            return false;
        }
    }
}
