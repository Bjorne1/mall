package com.wcs.mall.order.jpa;

import com.wcs.mall.order.entity.MallOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 15:01
 */
public interface JpaMallOrderProductDao extends JpaRepository<MallOrderProduct, String> {
    List<MallOrderProduct> findByOrderId(String orderId);
}
