package com.wcs.mall.address.entity;


import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
public class MallAddress extends BaseEntity {

  private String userId;
  private String receiverName;
  private String telephone;
  private String region;
  private String detailAddress;
  private int status;

}
