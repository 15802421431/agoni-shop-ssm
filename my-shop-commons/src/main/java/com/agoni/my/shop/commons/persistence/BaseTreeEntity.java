package com.agoni.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title BaseTreeEntity
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/21 14:56
 */
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;
    private boolean typeOfParent;

}
