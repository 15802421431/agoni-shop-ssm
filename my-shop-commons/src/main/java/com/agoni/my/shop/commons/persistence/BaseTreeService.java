package com.agoni.my.shop.commons.persistence;

import com.agoni.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * @Title BaseTreeService
 * @Description: 通用的树形结构接口
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/19 14:26
 */
public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询全部数据
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据 ID 查询信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);
    /**
     * 根据父级节点 ID 查询所有字节点
     * @param parentId
     * @return
     */
    List<T> selectByPid(Long parentId);
}
