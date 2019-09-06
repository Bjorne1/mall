package com.wcs.mall.order.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wcs.custom.util.IdUtil;
import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.cart.entity.MallCart;
import com.wcs.mall.cart.jpa.JpaMallCartDao;
import com.wcs.mall.order.entity.MallOrder;
import com.wcs.mall.order.entity.MallOrderDTO;
import com.wcs.mall.order.entity.MallOrderProduct;
import com.wcs.mall.order.entity.OrderProductDTO;
import com.wcs.mall.order.jpa.JpaMallOrderDao;
import com.wcs.mall.order.jpa.JpaMallOrderProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    @Autowired
    private JpaMallCartDao jpaMallCartDao;

    @Autowired
    private JpaMallOrderProductDao jpaMallOrderProductDao;

    public List<OrderProductDTO> findByStatusAndUserId(String userId, int status, int page, int size) {
        int start = (page - 1) * size;
        List<OrderProductDTO> orderProductDtoList = new ArrayList<>();
        List<MallOrder> mallList;
        if (status == Constant.STATUS_ALL_ORDER) { // 0:查找所有订单
            mallList = jpaMallOrderDao.findAllByUserId(userId, start, size);
        } else if (status == Constant.STATUS_DELETED_ORDER) { // 6:已关闭
            mallList = jpaMallOrderDao.findByUserIdAndDel(userId, Constant.DELETE_DATA, start, size);
        } else { // 1:待付款，2:已付款，3:待发货，4:待收货，5:已完成，
            mallList = jpaMallOrderDao.findByUserIdAndStatusAndDel(userId, status, Constant.NORMAL_DATA, start, size);
        }
        for (MallOrder mallOrder : mallList) {
            List<MallOrderProduct> orderProductList = jpaMallOrderProductDao.findByOrderId(mallOrder.getId());
            OrderProductDTO orderProductDto = new OrderProductDTO();
            orderProductDto.setOrder(mallOrder);
            orderProductDto.setOrderProductList(orderProductList);
            orderProductDtoList.add(orderProductDto);
        }
        return orderProductDtoList;
    }

    public void save(MallOrderDTO mallOrderDTO) {
        // TODO 未检查库存
        JSONArray cartIdArray = JSONObject.parseArray(mallOrderDTO.getCartIds());
        Iterator<Object> iterator = cartIdArray.iterator();
        List<String> cartIds = new ArrayList<>();
        while (iterator.hasNext()) {
            String id = (String) iterator.next();
            cartIds.add(id);
        }
        List<MallCart> cartList = jpaMallCartDao.findAllById(cartIds);
        int productCount = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        List<MallOrderProduct> orderProductList = new ArrayList<>();
        String orderId = IdUtil.nextId();
        for (MallCart cart : cartList) {
            totalPrice = totalPrice.add(cart.getProductPrice()); // 计算总价格
            productCount += cart.getProductCount(); // 计算总数量
            MallOrderProduct orderProduct = new MallOrderProduct();
            orderProduct.setId(IdUtil.nextId());
            orderProduct.setOrderId(orderId);
            orderProduct.setProductId(cart.getProductId());
            orderProduct.setProductName(cart.getProductName());
            orderProduct.setProductPrice(cart.getProductPrice());
            orderProduct.setProductCount(cart.getProductCount());
            orderProductList.add(orderProduct);
            cart.setDel(Constant.DELETE_DATA); // 购物车添加订单后购物车数据要删除
        }
        BigDecimal realPrice = new BigDecimal(0);
        if (mallOrderDTO.getUseCoupon()) { //如果使用优惠卷
            realPrice = totalPrice.subtract(mallOrderDTO.getCoupon());
        }
        String orderName = cartList.size() > 1 ?
                cartList.get(0).getProductName() + "等" + productCount + "件商品" : cartList.get(0).getProductName();
        MallOrder mallOrder = new MallOrder();
        mallOrder.setId(orderId);
        mallOrder.setUserId(mallOrderDTO.getUserId());
        mallOrder.setProductCount(productCount);
        mallOrder.setProductName(orderName);
        mallOrder.setTotalPrice(totalPrice);
        mallOrder.setRealPrice(realPrice);
        mallOrder.setAddress(mallOrderDTO.getAddress());
        mallOrder.setTelephone(mallOrderDTO.getTelephone());
        mallOrder.setReceivedName(mallOrderDTO.getReceivedName());
        mallOrder.setStatus(Constant.STATUS_TO_BE_PAY);
        jpaMallOrderDao.save(mallOrder);
        jpaMallOrderProductDao.saveAll(orderProductList);
        jpaMallCartDao.saveAll(cartList);
    }

    public int getCountByUserIdAndStatus(String userId, int status) {
        if (status == Constant.STATUS_ALL_ORDER) { // 所有订单数量
            return jpaMallOrderDao.getCountByUserIdAndDel(userId, Constant.NORMAL_DATA);

        } else if (status == Constant.STATUS_DELETED_ORDER) { // 关闭的订单
            return jpaMallOrderDao.getCountByUserIdAndDel(userId, Constant.DELETE_DATA);
        }
        return jpaMallOrderDao.getCountByUserIdAndStatusAndDel(userId, status, Constant.NORMAL_DATA);
    }

    public boolean delete(String id) {
        Optional<MallOrder> optionalMallOrder = jpaMallOrderDao.findById(id);
        if (optionalMallOrder.isPresent()) {
            MallOrder mallOrder = optionalMallOrder.get();
            System.out.println(mallOrder.getStatus());
            if (mallOrder.getStatus() != Constant.STATUS_TO_BE_PAY && mallOrder.getStatus() != Constant.STATUS_FINISHED) {
                return false;
            }
            mallOrder.setDel(Constant.DELETE_DATA);
            jpaMallOrderDao.save(mallOrder);
            List<MallOrderProduct> orderProductList = jpaMallOrderProductDao.findByOrderId(mallOrder.getId());
            for (MallOrderProduct mallOrderProduct : orderProductList) {
                mallOrderProduct.setDel(Constant.DELETE_DATA);
            }
            jpaMallOrderProductDao.saveAll(orderProductList);
            return true;
        }
        return false;
    }

}
