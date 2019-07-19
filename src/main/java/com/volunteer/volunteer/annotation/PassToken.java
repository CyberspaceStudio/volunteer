package com.volunteer.volunteer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Maolin
 * @className ：PassToken
 * @date ：Created in 2019/7/19 17:17
 * @description： 用来跳过验证的PassToken
 * @version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
