/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EmailSendUtils
 * Author:   Soulmate
 * Date:     2019/9/9 14:49
 * Description: 邮件发送工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮件发送工具类〉
 *
 * @author Soulmate
 * @create 2019/9/9
 * @since 1.0.0
 */
public class EmailSendUtils {

    @Autowired
    private Email email;

    public void send(String subject, String msg, String... to) throws EmailException {

        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
