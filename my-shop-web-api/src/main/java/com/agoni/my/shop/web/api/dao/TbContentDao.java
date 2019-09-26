package com.agoni.my.shop.web.api.dao;

import com.agoni.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title TbContentDao
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/27 17:01
 */

@Repository
public interface TbContentDao {
    /**
     * 根据类别 ID 查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
