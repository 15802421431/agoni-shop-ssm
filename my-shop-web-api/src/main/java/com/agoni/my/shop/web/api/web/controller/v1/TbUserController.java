/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TbUserController
 * Author:   Soulmate
 * Date:     2019/9/2 16:01
 * Description: 会员管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.api.web.controller.v1;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.domain.TbUser;
import com.agoni.my.shop.web.api.service.TbUserService;
import com.agoni.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员管理〉
 *
 * @author Soulmate
 * @create 2019/9/2
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if (user != null) {
            TbUserDTO data = new TbUserDTO();
            BeanUtils.copyProperties(user,data);
            return BaseResult.success("登录成功",data);
        }
        else {
            return BaseResult.fail("账号或密码错误");
        }
    }
}
