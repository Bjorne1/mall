package com.wcs.mall.cart.jpa;

import com.wcs.mall.cart.entity.MallCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 15:50
 */
public interface JpaMallCartDao extends JpaRepository<MallCart, String> {
    Page<MallCart> findAllByUserIdAndDel(Pageable pageable, String userId, int del);

    MallCart findByUserIdAndProductIdAndDel(String userId, String productId, int del);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart SET del=?2 WHERE user_id=?1", nativeQuery = true)
    void updateDelByUserIdAndDel(String userId, int del);
}
