package com.agoni.my.shop.web.admin.service.impl;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.validator.BeanValidator;
import com.agoni.my.shop.domain.TbContent;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.agoni.my.shop.web.admin.dao.TbContentDao;
import com.agoni.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Title TbContentServiceImpl
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/6/13 19:57
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {

        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //通过验证
        else {
            //设置更新时间
            tbContent.setUpdated(new Date());
            //新增
            if (tbContent.getId() == null) {
                //设置新建时间
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }
            //编辑
            else {
                update(tbContent);
            }
            return BaseResult.success("保存内容信息成功");
        }
    }

}