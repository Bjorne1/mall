package com.wcs.mall.base.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 11:26
 */
@Data
public class ResultBean<T> {

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ResultBean() {
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBean(Throwable e, int code) {
        this.message = e.toString();
        this.code = code;
    }

}
