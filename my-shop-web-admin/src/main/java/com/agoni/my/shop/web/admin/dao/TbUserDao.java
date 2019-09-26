package com.agoni.my.shop.web.admin.dao;

import com.agoni.my.shop.commons.persistence.BaseDao;
import com.agoni.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;


@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}