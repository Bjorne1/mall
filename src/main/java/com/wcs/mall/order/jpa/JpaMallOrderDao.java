package com.wcs.mall.order.jpa;

import com.wcs.mall.order.entity.MallOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 10:42
 */
public interface JpaMallOrderDao extends JpaRepository<MallOrder, String> {

    Page<MallOrder> findAllByUserId(String userId, Pageable pageable);

    Page<MallOrder> findByUserIdAndStatusAndDel(String userId, int status, int del, Pageable pageable);

    Page<MallOrder> findByUserIdAndDel(String userId, int del, Pageable pageable);
}
