/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TbUserDTO
 * Author:   Soulmate
 * Date:     2019/9/3 16:26
 * Description: 会员数据传输对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员数据传输对象〉
 *
 * @author Soulmate
 * @create 2019/9/3
 * @since 1.0.0
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
