package com.agoni.my.shop.web.admin.abstracts;

import com.agoni.my.shop.commons.persistence.BaseTreeEntity;
import com.agoni.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Title AbstractBaseTreeController
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/21 9:28
 */
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转到表单
     * @return
     */
    public abstract String form(T entity);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 排序
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   父节点的 ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId) {
        //遍历源数据
        for (T sourceEntity : sourceList) {
            //如果源数据的pid和传进来的pid相等，就往targetList中追加
            if ((sourceEntity.getParent().getId()).equals(parentId)) {
                targetList.add(sourceEntity);

                //判断有没有子节点，如果有则继续追加
                if (sourceEntity.isTypeOfParent()) {
                    //继续遍历一遍
                    for (T currentEntity : sourceList) {
                        //判断本数据的pid是否与上一个数据id相等
                        if (currentEntity.getParent().getId().equals(sourceEntity.getId())) {
                            //相等，则递归
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
