package com.wcs.mall.product.service;

import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.product.entity.MallProduct;
import com.wcs.mall.product.jpa.JpaMallProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 16:55
 */
@Service
public class MallProductService {
    @Autowired
    private JpaMallProductDao jpaMallProductDao;

    public Page<MallProduct> findAll(int page, int size) {
        // TODO 产品图
        Pageable pageable = PageRequest.of(page - 1, size);
        return jpaMallProductDao.findByDel(Constant.NORMAL_DATA, pageable);
    }
}
