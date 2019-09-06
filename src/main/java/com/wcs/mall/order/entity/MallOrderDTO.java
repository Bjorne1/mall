package com.wcs.mall.order.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 11:25
 */
@Data
public class MallOrderDTO {
    private String userId;
    private String cartIds;
    private Boolean useCoupon; //是否使用优惠卷
    private BigDecimal coupon;
    private String receivedName;
    private String telephone;
    private String address;
}
