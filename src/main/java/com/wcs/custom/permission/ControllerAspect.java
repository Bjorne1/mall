package com.wcs.custom.permission;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wcs.custom.util.TokenUtil;
import com.wcs.mall.base.entity.HttpStatusCode;
import com.wcs.mall.base.entity.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/2 15:28
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.wcs.mall.*.controller.*.*(..))")
    public void permission() {
    }

    @Pointcut("execution(public com.wcs.mall.base.entity.ResultBean *(..))")
    public void handlerException() {
    }

    /**
     * 权限切面
     */
    @Around("permission()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = pjp.getTarget().getClass().getMethod(ms.getName(), ms.getParameterTypes());
        Permission permission = method.getAnnotation(Permission.class);
        // 不需要权限
        if (permission == null || permission.type() == PermissionType.NO_PERMISSION) {
            return pjp.proceed();
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("Authorization");

        // 没有令牌
        if (StringUtils.isEmpty(token)) {
            return new ResultBean<>(HttpStatusCode.UNAUTHORIZED.value(), HttpStatusCode.UNAUTHORIZED.getReasonPhrase());
        }
        DecodedJWT decodedJwt = TokenUtil.verifyToken(token);
        // 需要登陆权限
        if (permission.type() == PermissionType.LOGIN_PERMISSION) {
            if (decodedJwt == null) {
                // 非法令牌
                return new ResultBean<>(HttpStatusCode.FORBIDDEN.value(), HttpStatusCode.FORBIDDEN.getReasonPhrase());
            } else {
                return pjp.proceed();
            }
        }
        Object[] args = pjp.getArgs();
        // 需要用户权限
        if (permission.type() == PermissionType.USER_PERMISSION) {
            if (decodedJwt == null) {
                return new ResultBean<>(HttpStatusCode.FORBIDDEN.value(), HttpStatusCode.FORBIDDEN.getReasonPhrase());
            }
            String username = decodedJwt.getClaim("username").asString();
            for (Object arg : args) {
                if (arg.equals(username)) { // 参数中有包含用户名
                    return pjp.proceed();
                }
            }
            // 参数中不包含用户名
            return new ResultBean<>(HttpStatusCode.FORBIDDEN.value(), HttpStatusCode.FORBIDDEN.getReasonPhrase());
        }
        // 需要特定的权限
        if (permission.type() == PermissionType.SPECIFIC_PERMISSION) {
            // TODO 特定权限
        }
        return new ResultBean<>(HttpStatusCode.UNAUTHORIZED.value(), HttpStatusCode.UNAUTHORIZED.getReasonPhrase());
    }

    /**
     * 处理和包装异常
     */
    @Around("handlerException()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        // 已知异常
        if (e instanceof JWTCreationException) {
            result.setMessage(HttpStatusCode.LOGIN_FAIL.getReasonPhrase());
            result.setCode(HttpStatusCode.LOGIN_FAIL.value());
        } else if (e instanceof DataIntegrityViolationException) {
            //用户名唯一ConstraintViolationException  数据过大是DataException
            System.out.println("报错信息:" + e.toString());
            if (e.toString().contains("ConstraintViolationException")) {
                result.setMessage(HttpStatusCode.USERNAME_IS_DUPLICATE.getReasonPhrase());
                result.setCode(HttpStatusCode.USERNAME_IS_DUPLICATE.value());
            } else if (e.toString().contains("DataException")) {
                result.setMessage(HttpStatusCode.DATE_IS_TOO_LONG.getReasonPhrase());
                result.setCode(HttpStatusCode.DATE_IS_TOO_LONG.value());
            } else {
                result.setMessage(HttpStatusCode.INTERNAL_SERVER_ERROR.getReasonPhrase());
                result.setCode(HttpStatusCode.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            logger.error(pjp.getSignature() + " error: ", e);
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMessage(HttpStatusCode.UNKNOWN.getReasonPhrase());
            result.setCode(HttpStatusCode.UNKNOWN.value());
        }
        return result;
    }
}
