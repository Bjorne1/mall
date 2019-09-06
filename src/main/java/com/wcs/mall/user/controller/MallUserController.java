package com.wcs.mall.user.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.user.entity.MallUser;
import com.wcs.mall.user.service.MallUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 11:47
 */
@Api(tags = {"用户信息"})
@RestController
@RequestMapping("/v1/user")
public class MallUserController {

    @Autowired
    private MallUserService mallUserService;

    @ApiOperation(value = "修改用户信息", tags = {"用户信息"})
    @PutMapping("/{username}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> update(@PathVariable String username, @RequestBody MallUser mallUser) {

        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

}
