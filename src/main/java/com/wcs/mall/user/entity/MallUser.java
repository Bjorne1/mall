package com.wcs.mall.user.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:05
 */
@Data
@Entity
@Table(name = "user")
public class MallUser extends BaseEntity {
    private String username;
    private String name;
    private String password;
    private int sex;
}
