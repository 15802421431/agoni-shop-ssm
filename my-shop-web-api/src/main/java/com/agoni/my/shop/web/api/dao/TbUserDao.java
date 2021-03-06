/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TbUserDao
 * Author:   Soulmate
 * Date:     2019/9/2 14:49
 * Description: 会员管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.api.dao;

import com.agoni.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员管理〉
 *
 * @author Soulmate
 * @create 2019/9/2
 * @since 1.0.0
 */
@Repository
public interface TbUserDao {
    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
