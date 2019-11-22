package com.wcs.mall.user.service;

import com.alibaba.fastjson.JSONObject;
import com.wcs.custom.util.BeanUtil;
import com.wcs.custom.util.IdUtil;
import com.wcs.custom.util.TokenUtil;
import com.wcs.mall.base.entity.Constant;
import com.wcs.mall.user.dao.MallUserDao;
import com.wcs.mall.user.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:06
 */
@Service
public class MallUserService {
    @Autowired
    private MallUserDao mallUserDao;

    public Page<MallUser> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return mallUserDao.findAllByDel(pageable, Constant.NORMAL_DATA);
    }

    public void add(MallUser mallUser) {
        mallUser.setId(IdUtil.nextId());
        mallUserDao.save(mallUser);
    }

    public void update(MallUser mallUser) {
        Optional<MallUser> mallUserOptional = mallUserDao.findById(mallUser.getId());
        if (mallUserOptional.isPresent()) {
            MallUser user = mallUserOptional.get();
            BeanUtil.copyProperties(mallUser,user);
            mallUserDao.save(user);
        }
    }

    public void delete(String id) {
        Optional<MallUser> mallUserOptional = mallUserDao.findById(id);
        if (mallUserOptional.isPresent()) {
            MallUser mallUser = mallUserOptional.get();
            mallUser.setDel(Constant.DELETE_DATA);
            mallUserDao.save(mallUser);
        }
    }

    /**
     * 用户登录，返回token和用户信息json
     */
    public JSONObject login(String username, String password) {
        MallUser mallUser = mallUserDao.findByUsernameAndDel(username, Constant.NORMAL_DATA);
        if (mallUser != null && mallUser.getPassword().equals(password)) {
            String token = TokenUtil.getToken(mallUser);
            JSONObject userInfos = filterUserInfos(mallUser);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", userInfos);
            jsonObject.put("token", token);
            return jsonObject;
        }
        return null;
    }

    /**
     * 过滤用户信息
     */
    private JSONObject filterUserInfos(MallUser mallUser) {
        JSONObject json = new JSONObject();
        json.put("id", mallUser.getId());
        json.put("username", mallUser.getUsername());
        json.put("name", mallUser.getName());
        json.put("sex", mallUser.getSex());
        json.put("update_time", mallUser.getUpdateTime());
        return json;
    }
}
