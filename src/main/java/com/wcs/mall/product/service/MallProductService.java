package com.wcs.mall.product.service;

import com.wcs.mall.product.jpa.JpaMallProductDao;
import org.springframework.beans.factory.annotation.Autowired;
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
}
