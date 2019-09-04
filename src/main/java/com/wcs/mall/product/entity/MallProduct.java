package com.wcs.mall.product.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 16:51
 */
@Data
@Entity
@Table(name = "product")
public class MallProduct extends BaseEntity {
    private String name;
    private BigDecimal price;
    private int sales;
    private int inventory;
    private int status;
}
