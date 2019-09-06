package com.wcs.mall.order.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.custom.util.TokenUtil;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.PageResultBean;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.order.entity.MallOrderDTO;
import com.wcs.mall.order.entity.OrderProductDTO;
import com.wcs.mall.order.service.MallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 10:41
 */
@Api(tags = "用户订单")
@RestController
@RequestMapping("/v1/user")
public class MallOrderController {

    @Autowired
    private MallOrderService mallOrderService;

    @ApiOperation(value = "获取订单", tags = {"用户订单"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "0:获取所有订单，1:待付款，2:已付款，3:待发货，4:待收货，5:已完成，6:已关闭", required = true, dataType = "int")
    })
    @GetMapping("/{username}/order")
    @Permission(type = PermissionType.USER_PERMISSION)
    public PageResultBean<OrderProductDTO> get(@PathVariable String username, HttpServletRequest request, int status, int page, int size) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        int count = mallOrderService.getCountByUserIdAndStatus(userId, status);
        List<OrderProductDTO> orderProductDtoList = new ArrayList<>();
        if (count != 0) {
            orderProductDtoList = mallOrderService.findByStatusAndUserId(userId, status, page, size);
        }
        return new PageResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), orderProductDtoList, count);
    }

    @ApiOperation(value = "新增订单", tags = {"用户订单"})
    @PostMapping("/{username}/order")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> save(@PathVariable String username, HttpServletRequest request, @RequestBody MallOrderDTO mallOrderDTO) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        mallOrderDTO.setUserId(userId);
        mallOrderService.save(mallOrderDTO);
        return new ResultBean<>(HttpStatusCode.CREATED.value(), HttpStatusCode.CREATED.getReasonPhrase());
    }

    @ApiOperation(value = "删除订单", tags = {"用户订单"})
    @DeleteMapping("/{username}/order/{id}")
    @Permission(type = PermissionType.USER_PERMISSION)
    public ResultBean<Void> delete(@PathVariable String id, @PathVariable String username) {
        boolean success = mallOrderService.delete(id);
        if (success) {
            return new ResultBean<>(HttpStatusCode.NO_CONTENT.value(), HttpStatusCode.NO_CONTENT.getReasonPhrase());
        }
        return new ResultBean<>(HttpStatusCode.DELETE_FAIL.value(), HttpStatusCode.DELETE_FAIL.getReasonPhrase());
    }
}
