package com.agoni.my.shop.web.admin.service;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.dto.PageInfo;
import com.agoni.my.shop.commons.persistence.BaseDao;
import com.agoni.my.shop.commons.persistence.BaseService;
import com.agoni.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);
}
