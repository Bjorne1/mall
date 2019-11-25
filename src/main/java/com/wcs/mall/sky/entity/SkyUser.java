package com.wcs.mall.sky.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:05
 */
@Data
public class SkyUser extends BaseEntity {
    private String username;
    private String name;
    private String password;
    private int sex;
}
