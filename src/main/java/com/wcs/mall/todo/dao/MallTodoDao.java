package com.wcs.mall.todo.dao;

import com.wcs.custom.mybatis.DataSource;
import com.wcs.mall.todo.entity.MallTodo;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/11/22 14:40
 */
@DataSource("mall")
public interface MallTodoDao {

    List<MallTodo> findAll();

    MallTodo findOne();
}
