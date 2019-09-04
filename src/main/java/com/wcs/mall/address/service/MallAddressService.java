package com.wcs.mall.address.service;

import com.wcs.custom.util.BeanUtil;
import com.wcs.custom.util.IdUtil;
import com.wcs.mall.address.jpa.JpaMallAddressDao;
import com.wcs.mall.address.dao.MallAddressDao;
import com.wcs.mall.address.entity.MallAddress;
import com.wcs.mall.base.entity.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/3 14:24
 */
@Service
public class MallAddressService {

    @Autowired
    private JpaMallAddressDao jpaMallAddressDao;

    @Autowired
    private MallAddressDao mallAddressDao;

    public Page<MallAddress> findAll(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return jpaMallAddressDao.findAllByUserIdAndDel(pageable, userId, Constant.NORMAL_DATA);
    }

    public void update(MallAddress mallAddress) {
        Optional<MallAddress> addressOptional = jpaMallAddressDao.findById(mallAddress.getId());
        if (addressOptional.isPresent()) {
            MallAddress address = addressOptional.get();
            BeanUtil.copyProperties(mallAddress, address);
            setDefaultAddress(address);
            jpaMallAddressDao.save(address);
        }
    }

    public void save(MallAddress mallAddress) {
        mallAddress.setId(IdUtil.nextId());
        jpaMallAddressDao.save(mallAddress);
        setDefaultAddress(mallAddress);
    }

    private void setDefaultAddress(MallAddress address) {
        if (address.getStatus() == Constant.DEFAULT_ADDRESS) {
            //修改为默认收货地址
            mallAddressDao.updateStatusByUserId(address.getId(), address.getUserId());
        }
    }

    public void delete(String id) {
        Optional<MallAddress> addressOptional = jpaMallAddressDao.findById(id);
        if (addressOptional.isPresent()) {
            MallAddress address = addressOptional.get();
            address.setDel(Constant.DELETE_DATA);
            jpaMallAddressDao.save(address);
        }
    }
}
