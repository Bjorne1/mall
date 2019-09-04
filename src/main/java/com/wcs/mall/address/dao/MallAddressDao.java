package com.wcs.mall.address.dao;

import org.apache.ibatis.annotations.Param;

public interface MallAddressDao {

    void updateStatusByUserId(@Param("id") String id, @Param("userId") String userId);

}
