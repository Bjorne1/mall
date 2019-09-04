package com.wcs.custom.permission;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 15:26
 */
public enum PermissionType {
    /**
     * 不需要权限
     */
    NO_PERMISSION,
    /**
     * 需要登录权限
     */
    LOGIN_PERMISSION,
    /**
     * 需要用户权限
     */
    USER_PERMISSION,
    /**
     * 需要特定的权限
     */
    SPECIFIC_PERMISSION
}
