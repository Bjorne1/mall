package com.wcs.custom.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: Spring那些BeanUtil，当对象为null的时候也会赋值过去
 * 这里的BeanUtil只有当不为null时才复制
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/3 16:17
 */
public class BeanUtil {

    /**
     * 源对象属性为Null时忽略
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(source);
                if (value != null) {
                    BeanUtils.setProperty(target, name, value);
                }
                field.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
