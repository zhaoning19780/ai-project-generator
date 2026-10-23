package com.gpjy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * JsonConfig class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO Spring MVC Json配置
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@JsonComponent
public class JsonConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE,ToStringSerializer.instance);
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
