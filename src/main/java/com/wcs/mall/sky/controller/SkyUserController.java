package com.wcs.mall.sky.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.sky.entity.SkyUser;
import com.wcs.mall.sky.service.SkyUserService;
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
 * @Date: Created in 2019/9/2 11:47
 */
@Api(tags = {"sky用户信息"})
@RestController
@RequestMapping("/v1/skyUser")
public class SkyUserController {

    @Autowired
    private SkyUserService mallUserService;

    @ApiOperation(value = "获取sky用户", tags = {"sky用户信息"})
    @GetMapping
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<List<SkyUser>> findAll() {
        List<SkyUser> mallUserList = mallUserService.findAll();
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallUserList);
    }

}
