package com.wcs.custom.mybatis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * mybatis切面，用来切换数据源
 *
 * @author wcs
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.wcs.mall.*.dao.*.*(..))")
    public void dataSourcePointCut() {

    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        DataSource dataSource = target.getClass().getInterfaces()[0].getAnnotation(DataSource.class);
        if (dataSource != null) {
            String key = dataSource.value();
            System.out.println("当前运行的数据源为：" + key);
            DynamicDataSourceHolder.setDataSource(key);
        }
    }
}
