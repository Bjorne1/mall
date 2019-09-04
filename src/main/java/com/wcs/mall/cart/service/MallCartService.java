package com.wcs.mall.cart.service;

import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.cart.entity.MallCart;
import com.wcs.mall.cart.jpa.JpaMallCartDao;
import com.wcs.mall.product.entity.MallProduct;
import com.wcs.mall.product.jpa.JpaMallProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 15:51
 */
@Service
public class MallCartService {

    @Autowired
    private JpaMallCartDao jpaMallCartDao;

    @Autowired
    private JpaMallProductDao jpaMallProductDao;

    public Page<MallCart> findAll(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return jpaMallCartDao.findAllByUserIdAndDel(pageable, userId, Constant.NORMAL_DATA);
    }

    public void save(String userId, String productId) {
        MallCart mallCart = jpaMallCartDao.findByUserIdAndProductIdAndDel(userId, productId, Constant.NORMAL_DATA);
        // 购物车已存在该商品，则数量加1
        if (mallCart != null) {
            mallCart.setProductCount(mallCart.getProductCount() + 1);
        } else {
            // 购物车不存在相同商品
            Optional<MallProduct> productOptional = jpaMallProductDao.findByIdAndDel(productId, Constant.NORMAL_DATA);
            if (productOptional.isPresent()) {
                MallProduct mallProduct = productOptional.get();
                mallCart = new MallCart();
                mallCart.setUserId(userId);
                mallCart.setProductId(productId);
                mallCart.setProductName(mallProduct.getName());
                mallCart.setProductPrice(mallProduct.getPrice());
                mallCart.setProductCount(1);
            }
        }
        jpaMallCartDao.save(mallCart);
    }

    public void updateProductCount(String id, int productCount) {
        Optional<MallCart> optional = jpaMallCartDao.findByIdAndDel(id, Constant.NORMAL_DATA);
        if (optional.isPresent()) {
            MallCart mallCart = optional.get();
            mallCart.setProductCount(productCount);
            jpaMallCartDao.save(mallCart);
        }
    }

    public void delete(String id) {
        Optional<MallCart> optional = jpaMallCartDao.findById(id);
        if (optional.isPresent()) {
            MallCart mallCart = optional.get();
            mallCart.setDel(Constant.DELETE_DATA);
            jpaMallCartDao.save(mallCart);
        }
    }

    public void deleteAllByUserId(String userId) {
        jpaMallCartDao.deleteByUserId(userId);
    }
}
