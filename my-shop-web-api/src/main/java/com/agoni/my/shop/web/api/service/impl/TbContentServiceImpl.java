package com.agoni.my.shop.web.api.service.impl;

import com.agoni.my.shop.domain.TbContent;
import com.agoni.my.shop.domain.TbContentCategory;
import com.agoni.my.shop.web.api.dao.TbContentDao;
import com.agoni.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title TbContentServiceimpl
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/28 7:06
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {

        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);
    }
}
