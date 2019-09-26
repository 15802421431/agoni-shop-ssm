package com.agoni.my.shop.web.api.service;

import com.agoni.my.shop.domain.TbContent;

import java.util.List;

/**
 * @Title TbContentService
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/28 7:05
 */

public interface TbContentService {
    List<TbContent> selectByCategoryId(Long categoryId);
}
