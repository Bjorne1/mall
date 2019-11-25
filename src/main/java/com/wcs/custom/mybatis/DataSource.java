package com.wcs.custom.mybatis;

import java.lang.annotation.*;

/**
 * mybatis自定义注解,用来声明数据源
 * @author wcs
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	String value();
}
