package com.wcs.mall.base.entity;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 14:29
 */
public enum HttpStatusCode {

    OK(200, "OK"),

    CREATED(201, "新建或修改成功"),

    NO_CONTENT(204, "删除成功"),

    BAD_REQUEST(400, "新建或修改成功"),

    UNAUTHORIZED(401, "用户名或密码错误"),

    FORBIDDEN(403, "权限不足"),

    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),

    LOGIN_FAIL(501, "登录失败，请稍后重试"),

    UNKNOWN(777, "未知错误"),

    DELETE_FAIL(666, "删除失败"),

    USERNAME_IS_DUPLICATE(888, "用户名已存在"),

    DATE_IS_TOO_LONG(999, "数据长度过长");

    private final int value;

    private final String reasonPhrase;

    HttpStatusCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
