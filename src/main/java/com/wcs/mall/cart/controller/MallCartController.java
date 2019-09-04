package com.wcs.mall.cart.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.custom.util.TokenUtil;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.cart.entity.MallCart;
import com.wcs.mall.cart.service.MallCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/4 15:51
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/v1/user")
public class MallCartController {

    @Autowired
    private MallCartService mallCartService;

    @ApiOperation(value = "获取购物车", tags = {"购物车"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int")
    })
    @GetMapping("/{username}/cart")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Page<MallCart>> get(@PathVariable String username, HttpServletRequest request, int page, int size) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        Page<MallCart> mallCartList = mallCartService.findAll(userId, page, size);
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallCartList);
    }

    @ApiOperation(value = "新增购物车", tags = {"购物车"})
    @ApiImplicitParam(name = "productId", value = "productId", required = true, dataType = "String")
    @PostMapping("/{username}/cart")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> update(@PathVariable String username, HttpServletRequest request, String productId) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        mallCartService.save(userId, productId);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "修改购物车产品数量", tags = {"购物车"})
    @PutMapping("/{username}/cart/{id}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> updateCount(@PathVariable String id, @PathVariable String username, int productCount) {
        mallCartService.updateProductCount(id, productCount);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "删除购物车", tags = {"购物车"})
    @DeleteMapping("/{username}/cart/{id}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> delete(@PathVariable String id, @PathVariable String username) {
        mallCartService.delete(id);
        return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
    }

    @ApiOperation(value = "清空购物车", tags = {"购物车"})
    @DeleteMapping("/{username}/cart")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> deleteAll(@PathVariable String id, @PathVariable String username, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        mallCartService.deleteAllByUserId(userId);
        return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
    }
}
