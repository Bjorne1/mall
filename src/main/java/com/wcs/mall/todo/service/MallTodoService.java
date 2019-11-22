package com.wcs.mall.todo.service;

import com.wcs.mall.todo.dao.MallTodoDao;
import com.wcs.mall.todo.entity.MallTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/11/22 14:40
 */
@Service
public class MallTodoService {

    @Autowired
    private MallTodoDao mallTodoDao;

    public List<MallTodo> findAll() {
        return mallTodoDao.findAll();
    }

    public MallTodo findOne() {
        return mallTodoDao.findOne();
    }
}
