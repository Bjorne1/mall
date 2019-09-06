package com.wcs.mall.order.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 14:53
 */
@Data
@Entity
@Table(name = "order_product")
public class MallOrderProduct extends BaseEntity {
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private int productCount;
}
