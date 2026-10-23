package com.gpjy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AuthCheck class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO 自定义权限校验注解
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    // 必须有某个角色
    String mustRole() default "";
}
