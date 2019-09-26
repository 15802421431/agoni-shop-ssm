package com.agoni.my.shop.domain;

import com.agoni.my.shop.commons.persistence.BaseTreeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Title TbContentCategory
 * @Description: 分类管理
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/6/12 14:12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbContentCategory extends BaseTreeEntity {
    @Length(min = 1, max = 20, message = "分类名称必须介于 1 - 20 位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private boolean typeOfParent;
    private TbContentCategory parent;

}