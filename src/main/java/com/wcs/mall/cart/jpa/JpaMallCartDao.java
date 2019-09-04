package com.wcs.mall.cart.jpa;

import com.wcs.mall.cart.entity.MallCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 15:50
 */
public interface JpaMallCartDao extends JpaRepository<MallCart, String> {
    Page<MallCart> findAllByUserIdAndDel(Pageable pageable, String userId, int del);

    MallCart findByUserIdAndProductIdAndDel(String userId, String productId, int del);

    Optional<MallCart> findByIdAndDel(String id, int del);

    void deleteByUserId(String userId);
}
