package com.agoni.my.shop.commons.persistence;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @Title BaseService
 * @Description: 所有业务逻辑层的基类
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/15 13:58
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 查询全部
     * @return
     */
    List<T> selectAll();

    /**
     * 保存信息
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 通过 id 删除信息
     * @param id
     */
    void delete(Long id);

    /**
     * 通过 id 获取信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新信息
     * @param entity
     */
    void update(T entity);

    /**
     * 批量删除
     * @param array
     */
    void deleteMulti(String[] array);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    PageInfo<T> page(int draw , int start, int length, T entity);

    /**
     * 查询总笔数
     * @param entity
     * @return
     */
    int count(T entity);
}
