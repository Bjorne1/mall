package com.wcs.mall.product.service;

import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.product.entity.MallCategory;
import com.wcs.mall.product.jpa.JpaMallCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 16:27
 */
@Service
public class MallCategoryService {

    @Autowired
    private JpaMallCategoryDao jpaMallCategoryDao;

    public List<MallCategory> findAll() {
        return jpaMallCategoryDao.findAllByDel(Constant.NORMAL_DATA);
    }
}
