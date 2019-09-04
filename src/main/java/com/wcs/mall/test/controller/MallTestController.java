package com.wcs.mall.test.controller;

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
 * @Date: Created in 2019/8/29 11:06
 */
@Api(tags = {"测试接口"})
@RestController
@RequestMapping("/v1/user")
public class MallTestController {

    @Autowired
    private MallUserService mallUserService;

    @ApiOperation(value = "查找用户（不需要权限）", tags = {"测试接口"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int")
    })
    @GetMapping
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<Page<MallUser>> findAll(int page, int size) {
        Page<MallUser> pageMallUser = mallUserService.findAll(page, size);
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), pageMallUser);
    }

    @ApiOperation(value = "添加用户（需要登录权限）", tags = {"测试接口"})
    @ApiImplicitParam(name = "mallUser", value = "用户实体", required = true, dataType = "MallUser")
    @PostMapping
    @Permission(type = PermissionType.LOGIN_PERMISSION)
    public ResultBean<Boolean> save(@RequestBody MallUser mallUser) {
        mallUserService.save(mallUser);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "修改用户（需要用户权限）", tags = {"测试接口"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mallUser", value = "用户实体", required = true, dataType = "MallUser"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @PutMapping(value = "/{id}/user/{username}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Boolean> update(@PathVariable String id,@PathVariable String username, @RequestBody MallUser mallUser) {
        mallUser.setId(id);
        mallUserService.update(mallUser);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "删除用户", tags = {"测试接口"})
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @DeleteMapping("/{id}")
    public ResultBean<Boolean> delete(@PathVariable String id) {
        mallUserService.delete(id);
        return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
    }
}
