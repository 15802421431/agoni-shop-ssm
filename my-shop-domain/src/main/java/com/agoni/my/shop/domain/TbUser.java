package com.agoni.my.shop.domain;

import com.agoni.my.shop.commons.persistence.BaseEntity;
import com.agoni.my.shop.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;//在编译时把getter,setter丢进去
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {

    @Length(min = 6,max = 18,message = "用户名长度必须介于 6 - 18 位之间")
    private String username;

    /**
     * JsonIgnore 是前端显示json数据的时候，密码忽略显示
     */
    @JsonIgnore
    @Length(min = 6, max = 18, message = "密码长度必须介于 6 - 18 位之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
}
