package com.wcs.mall.product.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 16:22
 */
@Data
@Entity
@Table(name = "category")
public class MallCategory extends BaseEntity {
    private String name;
}
