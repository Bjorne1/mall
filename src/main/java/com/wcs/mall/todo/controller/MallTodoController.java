package com.wcs.mall.todo.controller;

import com.wcs.custom.permission.Permission;
import com.wcs.custom.permission.PermissionType;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import com.wcs.mall.todo.entity.MallTodo;
import com.wcs.mall.todo.service.MallTodoService;
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
 * @Date: Created in 2019/11/22 14:34
 */
@Api(tags = {"代办信息"})
@RestController
@RequestMapping("/v1/todo")
public class MallTodoController {

    @Autowired
    private MallTodoService mallTodoService;

    @ApiOperation(value = "获取所有代办", tags = {"代办信息"})
    @GetMapping
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<List<MallTodo>> findAll() {
        List<MallTodo> mallTodoList = mallTodoService.findAll();
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallTodoList);
    }

    @ApiOperation(value = "获取单个代办", tags = {"代办信息"})
    @GetMapping("/one")
    @Permission(type = PermissionType.NO_PERMISSION)
    public ResultBean<MallTodo> findOne() {
        MallTodo mallTodo = mallTodoService.findOne();
        return new ResultBean<>(HttpStatusCode.OK.value(), HttpStatusCode.OK.getReasonPhrase(), mallTodo);
    }

}
