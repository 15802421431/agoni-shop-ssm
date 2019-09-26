package com.agoni.my.shop.web.admin.service.impl;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.validator.BeanValidator;
import com.agoni.my.shop.domain.TbContentCategory;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.agoni.my.shop.web.admin.dao.TbContentCategoryDao;
import com.agoni.my.shop.web.admin.service.TbContentCategoryService;
import com.agoni.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Title TbContentCategoryServiceImpl
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/6/12 15:54
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 保存分类
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        } else {
            TbContentCategory parent = entity.getParent();
            //如果没有选择父级节点则默认设置为根目录
            if (parent == null || parent.getId() == null) {
                // 0 代表根目录
                parent.setId(0L);
                //根目录一定是父级目录
                entity.setTypeOfParent(true);
            }

            entity.setUpdated(new Date());
            //新增
            if (entity.getId() == null) {
                entity.setCreated(new Date());

                //判断当前新增的节点有没有父级节点
                if (parent.getId() != 0L) {
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null) {
                        //为父级节点设置 isParent 为 true
                        currentCategoryParent.setTypeOfParent(true);
                        update(currentCategoryParent);
                    }
                }

                // 父级节点为 0 ，表示为根目录
                else {
                    // 根目录一定是父级目录
                    entity.setTypeOfParent(true);
                }

                dao.insert(entity);
            }

            //修改
            else {
                update(entity);
            }

            return BaseResult.success("保存信息成功");
        }
    }

}
