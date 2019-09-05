package com.wcs.mall.order.service;

import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.order.entity.MallOrder;
import com.wcs.mall.order.jpa.JpaMallOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 10:43
 */
@Service
public class MallOrderService {

    @Autowired
    private JpaMallOrderDao jpaMallOrderDao;

    public Page<MallOrder> findByStatusAndUserId(String userId, int status, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        if (status == Constant.STATUS_ALL_ORDER) { // 0:查找所有订单
            return jpaMallOrderDao.findAllByUserId(userId, pageable);
        } else if (status == Constant.STATUS_DELETED_ORDER) { // 6:已删除
            return jpaMallOrderDao.findByUserIdAndDel(userId, Constant.DELETE_DATA, pageable);
        } else { // 1:待付款，2:已付款，3:待发货，4:待收货，5:已完成，
            return jpaMallOrderDao.findByUserIdAndStatusAndDel(userId, status, Constant.NORMAL_DATA, pageable);
        }
    }

    public void delete(String id) {
        Optional<MallOrder> optionalMallOrder = jpaMallOrderDao.findById(id);
        if (optionalMallOrder.isPresent()) {
            MallOrder mallOrder = optionalMallOrder.get();
            mallOrder.setDel(Constant.DELETE_DATA);
            jpaMallOrderDao.save(mallOrder);
        }
    }
}
