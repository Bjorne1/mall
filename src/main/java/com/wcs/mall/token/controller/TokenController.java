package com.wcs.mall.token.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.user.service.MallUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 11:49
 */
@Api(tags = {"用户登录接口"})
@RestController
@RequestMapping("/v1/token")
public class TokenController {

    @Autowired
    private MallUserService mallUserService;

    @ApiOperation(value = "用户登录", tags = {"用户登录接口"})
    @GetMapping("/{username}")
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<String> getToken(@PathVariable String username, String password) {
        String token = mallUserService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            return new ResultBean<>(HttpStatusCode.UNAUTHORIZED.value(), HttpStatusCode.UNAUTHORIZED.getReasonPhrase());
        } else {
            return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), token);
        }
    }
}
