/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UsersApi
 * Author:   Soulmate
 * Date:     2019/9/2 20:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.ui.api;

import com.agoni.my.shop.commons.utils.HttpClientUtils;
import com.agoni.my.shop.commons.utils.MapperUtils;
import com.agoni.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员管理接口〉
 *
 * @author Soulmate
 * @create 2019/9/2
 * @since 1.0.0
 */
public class UsersApi {
    /**
     * 登录
     * @param tbUser
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));

        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}
