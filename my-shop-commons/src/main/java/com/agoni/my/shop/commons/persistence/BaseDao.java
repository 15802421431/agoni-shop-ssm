package com.agoni.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @Title BaseDao
 * @Description: 所有数据访问层的基类
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/15 10:50
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部数据
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

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
     * 批量删除
     */
    void deleteMulti(String[] array);

    /**
     * 分页查询
     * @param params 需要两个参数：start 记录数据开始的位置；length 每页记录数
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 返回总记录数（查询总笔数）
     * @param entity
     * @return
     */
    int count(T entity);
}
