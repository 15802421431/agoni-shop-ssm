/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TbContent
 * Author:   Soulmate
 * Date:     2019/9/1 13:47
 * Description: 数据传输对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据传输对象〉
 *
 * @author Soulmate
 * @create 2019/9/1
 * @since 1.0.0
 */
@Data
public class TbContent implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
