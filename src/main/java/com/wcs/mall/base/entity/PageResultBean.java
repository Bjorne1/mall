package com.wcs.mall.base.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:28
 */
@Data
public class PageResultBean<T> {
    private int code; //返回码
    private String message; //返回信息
    private List<T> data; //返回数据
    private int totalCount; //总数量

    public PageResultBean(int code, String message, List<T> data, int totalCount) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalCount = totalCount;
    }

    public PageResultBean(Throwable e, int code) {
        super();
        this.message = e.toString();
        this.code = code;
    }
}
