package com.agoni.my.shop.web.admin.abstracts;

import com.agoni.my.shop.commons.persistence.BaseEntity;
import com.agoni.my.shop.commons.persistence.BaseTreeDao;
import com.agoni.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title AbstractBaseTreeServiceImpl
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/19 15:54
 */
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 删除数据
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }
    /**
     * 根据 ID 查询信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id){
        return dao.getById(id);
    }

    /**
     * 更新
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity){
        dao.update(entity);
    }
    /**
     * 根据父级节点 ID 查询所有字节点
     * @param parentId
     * @return
     */
    @Override
    public List<T> selectByPid(Long parentId){
        return dao.selectByPid(parentId);
    }

}
