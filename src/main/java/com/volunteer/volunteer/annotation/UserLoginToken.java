package com.volunteer.volunteer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Maolin
 * @className ：UserLoginToken
 * @date ：Created in 2019/7/19 17:18
 * @description： 需要登录才能进行操作的注解
 * @version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}