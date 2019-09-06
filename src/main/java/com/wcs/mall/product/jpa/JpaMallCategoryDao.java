package com.wcs.mall.product.jpa;

import com.wcs.mall.product.entity.MallCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 16:26
 */
public interface JpaMallCategoryDao extends JpaRepository<MallCategory, String> {
    List<MallCategory> findAllByDel(int del);
}
