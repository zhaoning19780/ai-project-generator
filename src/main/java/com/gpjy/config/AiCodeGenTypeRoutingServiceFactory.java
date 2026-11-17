package com.gpjy.config;

import com.gpjy.ai.AiCodeGenTypeRoutingService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AiCodeGenTypeRoutingServiceFactory class
 *
 * @author Ning
 * @date 2026/11/17
 * @Description: TODO AI智能路由服务工厂类
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Configuration
public class AiCodeGenTypeRoutingServiceFactory {

    @Resource
    private ChatModel chatModel;

    // 创建AI代码生成类型路由服务实例
    @Bean
    public AiCodeGenTypeRoutingService aiCodeGenTypeRoutingService(){
        return AiServices.builder(AiCodeGenTypeRoutingService.class)
                .chatModel(chatModel)
                .build();
    }
}
