package com.wcs.mall.order.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 10:38
 */
@Data
@Entity
@Table(name = "order")
public class MallOrder extends BaseEntity {
    private String userId;
    private String productCount;
    private String totalPrice;
    private String coupon;
    private String realPrice;
    private int status;
    private String receivedName;
    private String telephone;
    private String address;
    private String expressNo;
}
