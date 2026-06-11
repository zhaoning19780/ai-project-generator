package com.gpjy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CrossOriginConfig class
 *
 * @author Ning
 * @Description: TODO 跨域配置类，解决前端跨域问题。
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求
        registry.addMapping("/**")
                // 允许发送Cookie
                .allowCredentials(true)
                // 放行哪些域名(必须用patterns，否则 * 会和 allowCredentials 冲突)
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
