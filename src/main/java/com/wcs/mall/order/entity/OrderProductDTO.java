package com.wcs.mall.order.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 15:11
 */
@Data
public class OrderProductDTO {
    private MallOrder order;
    private List<MallOrderProduct> orderProductList;
}
