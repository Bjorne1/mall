package com.wcs.mall.sky.service;

import com.wcs.mall.sky.dao.SkyUserDao;
import com.wcs.mall.sky.entity.SkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:06
 */
@Service
public class SkyUserService {
    @Autowired
    private SkyUserDao skyUserDao;

    public List<SkyUser> findAll() {
        return skyUserDao.findAll();
    }

}
