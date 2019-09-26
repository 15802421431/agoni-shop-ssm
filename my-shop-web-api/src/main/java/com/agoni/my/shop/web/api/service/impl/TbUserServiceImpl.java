/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TbUserServiceImpl
 * Author:   Soulmate
 * Date:     2019/9/2 15:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.api.service.impl;

import com.agoni.my.shop.domain.TbUser;
import com.agoni.my.shop.web.api.dao.TbUserDao;
import com.agoni.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Soulmate
 * @create 2019/9/2
 * @since 1.0.0
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {

        TbUser user = tbUserDao.login(tbUser);

        if (user != null) {
            //将明文密码加密
             String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());

             if (password.equals(user.getPassword())) {
                 return user;
             }
        }
        return null;
    }
}
