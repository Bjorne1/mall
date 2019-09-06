package com.wcs.mall.order.dao;

import com.wcs.mall.order.entity.MallOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderDao {

    int insert(@Param("pojo") MallOrder pojo);

    int insertList(@Param("pojos") List< MallOrder> pojo);

    List<MallOrder> select(@Param("pojo") MallOrder pojo);

    int update(@Param("pojo") MallOrder pojo);

}
