package com.wcs.mall.address.entity;


import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "address")
public class MallAddress extends BaseEntity {
    private String userId;
    @Size(max = 10, message = "收件人名称长度为1-10位")
    @NotBlank(message = "收件人名称不能为空")
    private String receiverName;
    @NotBlank(message = "手机号不能为空")
    @Size(max = 20, message = "手机号长度为1-20位")
    private String telephone;
    @NotBlank(message = "省市县不能为空")
    private String region;
    @NotBlank(message = "详细地址不能为空")
    @Size(max = 50,message = "详细地址长度为1-50位")
    private String detailAddress;
    private int status;

}
