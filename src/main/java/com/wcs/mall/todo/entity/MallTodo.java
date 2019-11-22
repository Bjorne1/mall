package com.wcs.mall.todo.entity;

import com.wcs.mall.base.entity.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/11/22 14:34
 */
@Data
public class MallTodo extends BaseEntity {
    private String todo;
    private String flag;
}
