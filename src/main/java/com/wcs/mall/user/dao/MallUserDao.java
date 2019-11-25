package com.wcs.mall.user.dao;

import com.wcs.mall.user.entity.MallUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:05
 */
public interface MallUserDao extends JpaRepository<MallUser, String> {

    Page<MallUser> findAllByDel(Pageable pageable, int del);

    MallUser findByUsernameAndDel(String username, int del);
}
