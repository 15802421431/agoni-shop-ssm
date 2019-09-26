package com.agoni.my.shop.web.admin.service.impl;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.validator.BeanValidator;
import com.agoni.my.shop.domain.TbUser;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.agoni.my.shop.web.admin.dao.TbUserDao;
import com.agoni.my.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {

        String validator = BeanValidator.validator(tbUser);
        //验证不通过  validator里面有东西证明有错误信息，要是通过就返回null
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //通过验证
        else {
            tbUser.setUpdated(new Date());
            //新增用户
            if(tbUser.getId() == null){
                //密码加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }
            //编辑用户
            else {
                update(tbUser);
            }
            return BaseResult.success("保存内容信息成功");
        }
    }

    @Override
    public TbUser login(String email, String password) {
        //根据邮箱查找用户
        TbUser tbUser = dao.getByEmail(email);
        //判断用户是否存在，存在进入，否则返回null
        if (tbUser != null){
            //明文密码加密
            String MD5_Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //将加密后的密码与数据库中存储的密码进行对比
            if (MD5_Password.equals(tbUser.getPassword())){
                //成功
                return tbUser;
            }
        }
        //失败
        return null;
    }
}
