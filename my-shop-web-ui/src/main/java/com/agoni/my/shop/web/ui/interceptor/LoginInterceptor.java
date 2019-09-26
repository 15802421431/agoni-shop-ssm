/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginInterceptor
 * Author:   Soulmate
 * Date:     2019/9/4 7:54
 * Description: 登录拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.ui.interceptor;

import com.agoni.my.shop.web.ui.constant.SystemConstant;
import com.agoni.my.shop.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈登录拦截器〉
 *
 * @author Soulmate
 * @create 2019/9/4
 * @since 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TbUser tbUser = (TbUser) request.getSession().getAttribute(SystemConstant.SESSION_USER_KEY);

        //未登录状态
        if (tbUser == null) {
            return true;
        }
        //已登录状态
        else {
            response.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
