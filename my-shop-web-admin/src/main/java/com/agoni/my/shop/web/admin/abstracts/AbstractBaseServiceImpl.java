package com.agoni.my.shop.web.admin.abstracts;

import com.agoni.my.shop.commons.dto.PageInfo;
import com.agoni.my.shop.commons.persistence.BaseDao;
import com.agoni.my.shop.commons.persistence.BaseEntity;
import com.agoni.my.shop.commons.persistence.BaseService;
import com.agoni.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title AbstractBaseServiceImpl
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/19 20:18
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 通过 id 删除信息
     * @param id
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 通过 id 获取信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新信息
     * @param entity
     */
    @Override
    public void update(T entity){
        dao.update(entity);
    }

    /**
     * 批量删除
     * @param array
     */
    @Override
    public void deleteMulti(String[] array){
        dao.deleteMulti(array);
    }

    /**
     * 查询总笔数
     * @param entity
     * @return
     */
    @Override
    public int count(T entity){
        return dao.count(entity);
    }

    /**
     * 分页查询
     * @param draw
     * @param start
     * @param length
     * @param entity
     * @return
     */
    public PageInfo<T> page(int start, int length, int draw, T entity) {

        int count = count(entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams", entity);
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));
        return pageInfo;
    }

}
