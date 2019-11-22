package com.wcs.mall.user.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.user.entity.MallUser;
import com.wcs.mall.user.service.MallUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @ApiOperation(value = "获取用户", tags = {"用户信息"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int")
    })
    @GetMapping
    @Permission(type = PermissionType.LOGIN_PERMISSION)
    public ResultBean<Page<MallUser>> findAll(int page, int size) {
        Page<MallUser> mallUserList = mallUserService.findAll(page, size);
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallUserList);
    }

    @ApiOperation(value = "新增用户", tags = {"用户信息"})
    @PostMapping
    @Permission(type = PermissionType.LOGIN_PERMISSION)
    public ResultBean<Void> add(@RequestBody MallUser mallUser) {
        mallUserService.add(mallUser);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "修改用户信息", tags = {"用户信息"})
    @PutMapping("/{id}")
    @Permission(type = PermissionType.LOGIN_PERMISSION)
    public ResultBean<Void> update(@PathVariable String id, @RequestBody MallUser mallUser) {
        System.out.println(mallUser.getName());
        mallUser.setId(id);
        mallUserService.update(mallUser);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "删除用户信息", tags = {"用户信息"})
    @DeleteMapping("/{id}")
    @Permission(type = PermissionType.LOGIN_PERMISSION)
    public ResultBean<Void> delete(@PathVariable String id) {
        mallUserService.delete(id);
        return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
    }

}
