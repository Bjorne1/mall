package com.wcs.mall.address.jpa;

import com.wcs.mall.address.entity.MallAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/3 14:23
 */
@Repository
public interface JpaMallAddressDao extends JpaRepository<MallAddress, String> {

    Page<MallAddress> findAllByUserIdAndDel(Pageable pageable, String userId, int del);

}
