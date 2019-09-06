package com.wcs.mall.order.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 11:25
 */
@Data
public class MallOrderDTO {
    private String userId;
    @NotBlank(message = "商品不能为空")
    private String cartIds;
    private Boolean useCoupon; //是否使用优惠卷
    private BigDecimal coupon;
    @NotBlank(message = "收件人不能为空")
    @Size(max = 10, message = "收件人名称长度为1-10位")
    private String receivedName;
    @NotBlank(message = "手机号不能为空")
    @Size(max = 20, message = "手机号长度为1-20位")
    private String telephone;
    @NotBlank(message = "地址不能为空")
    @Size(max = 50, message = "详细地址长度为1-50位")
    private String address;
}
