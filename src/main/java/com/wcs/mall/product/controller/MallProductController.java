package com.wcs.mall.product.controller;

import com.wcs.mall.product.service.MallProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 16:55
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/v1/user")
public class MallProductController {

    @Autowired
    private MallProductService mallProductService;
}
