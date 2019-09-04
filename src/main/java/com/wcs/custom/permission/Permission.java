package com.wcs.custom.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 15:25
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    /**
     * 所需权限类型
     *
     */
    PermissionType type() default PermissionType.NO_PERMISSION;

    /**
     * 具体权限的值
     *
     */
    String value() default "";
}
