package com.wcs.mall.product.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.product.entity.MallProduct;
import com.wcs.mall.product.service.MallProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 16:55
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/v1/product")
public class MallProductController {

    @Autowired
    private MallProductService mallProductService;

    @ApiOperation(value = "获取商品信息", tags = {"商品信息"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int")})
    @GetMapping
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<Page<MallProduct>> get(int page, int size) {
        Page<MallProduct> productPage = mallProductService.findAll(page, size);
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), productPage);
    }
}
