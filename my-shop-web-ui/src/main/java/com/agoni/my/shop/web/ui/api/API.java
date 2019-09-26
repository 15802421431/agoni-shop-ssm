/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: API
 * Author:   Soulmate
 * Date:     2019/9/1 17:08
 * Description: 接口常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.ui.api;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接口常量〉
 *
 * @author Soulmate
 * @create 2019/9/1
 * @since 1.0.0
 */
public class API {
    //主机地址
    public static final String HOST = "http://localhost:8081/api/v1";

    //内容查询接口
    public static final String API_CONTENTS = HOST + "/contents/";

    //会员管理接口 - 登录
    public static final String API_USERS_LOGIN = HOST + "/users/login";
}
