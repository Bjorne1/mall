package com.wcs.mall.user.controller;

import com.wcs.mall.user.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 11:47
 */
@RestController
@RequestMapping("/v1/user")
public class MallUserController {

    @Autowired
    private MallUserService mallUserService;

}
