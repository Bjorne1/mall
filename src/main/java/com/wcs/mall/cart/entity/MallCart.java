package com.wcs.mall.cart.entity;


import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cart")
public class MallCart extends BaseEntity {
    private String userId;
    private String productId;
    private String productName;
    private int productCount;
    private BigDecimal productPrice;
}
