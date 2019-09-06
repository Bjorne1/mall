package com.wcs.mall.address.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.custom.util.TokenUtil;
import com.wcs.mall.address.entity.MallAddress;
import com.wcs.mall.address.service.MallAddressService;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/3 14:25
 */
@Api(tags = {"收获地址"})
@RestController
@RequestMapping("/v1/user")
public class MallAddressController {

    @Autowired
    private MallAddressService mallAddressService;

    @ApiOperation(value = "获取收获地址", tags = {"收获地址"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int")
    })
    @GetMapping("/{username}/address")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Page<MallAddress>> findAll(@PathVariable String username, HttpServletRequest request, int page, int size) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        Page<MallAddress> mallAddressList = mallAddressService.findAll(userId, page, size);
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallAddressList);
    }

    @ApiOperation(value = "新增收获地址", tags = {"收获地址"})
    @PostMapping("/{username}/address")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> save(@PathVariable String username, @RequestBody @Valid MallAddress mallAddress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResultBean<>(HttpStatusCode.BAD_REQUEST.value(), Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        mallAddressService.save(mallAddress);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "修改收获地址", tags = {"收获地址"})
    @PutMapping("/{username}/address/{id}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> update(@PathVariable String id, @PathVariable String username, @RequestBody MallAddress mallAddress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResultBean<>(HttpStatusCode.BAD_REQUEST.value(), Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        mallAddress.setId(id);
        mallAddressService.update(mallAddress);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "删除收获地址", tags = {"收获地址"})
    @DeleteMapping("/{username}/address/{id}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> delete(@PathVariable String id, @PathVariable String username) {
        mallAddressService.delete(id);
        return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
    }
}
