package com.wcs.mall.sky.dao;

import com.wcs.custom.mybatis.DataSource;
import com.wcs.mall.sky.entity.SkyUser;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:05
 */
@DataSource("sky")
public interface SkyUserDao {

    List<SkyUser> findAll();
}
