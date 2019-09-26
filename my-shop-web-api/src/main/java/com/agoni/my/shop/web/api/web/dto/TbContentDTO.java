package com.agoni.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title TbContentDTO
 * @Description: 内容数据传输对象
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/28 16:30
 */
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
