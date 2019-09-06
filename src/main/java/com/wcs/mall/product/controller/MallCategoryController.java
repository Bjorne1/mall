package com.wcs.mall.product.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.product.entity.MallCategory;
import com.wcs.mall.product.service.MallCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 16:28
 */
@Api(tags = "商品类别")
@RestController
@RequestMapping("/v1/category")
public class MallCategoryController {

    @Autowired
    private MallCategoryService mallCategoryService;

    @ApiOperation(value = "获取商品类别", tags = {"商品类别"})
    @GetMapping
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<List<MallCategory>> get() {
        List<MallCategory> mallCategoryList = mallCategoryService.findAll();
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallCategoryList);
    }
}
