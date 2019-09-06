package com.wcs.mall.product.jpa;

import com.wcs.mall.product.entity.MallProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 16:54
 */
public interface JpaMallProductDao extends JpaRepository<MallProduct, String> {
    Optional<MallProduct> findByIdAndDel(String productId, int del);

    Page<MallProduct> findByDel(int del, Pageable pageable);
}
