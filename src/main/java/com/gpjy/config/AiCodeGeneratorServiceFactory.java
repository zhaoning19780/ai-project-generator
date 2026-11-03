package com.gpjy.config;

import com.gpjy.ai.AiCodeGeneratorService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * AiCodeGeneratorServiceFactory class
 *
 * @author Ning
 * @date 2026/10/24
 * @Description: TODO 创建工厂类初始化AI服务
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Configuration
public class AiCodeGeneratorServiceFactory {

    @Value("${langchain4j.open-ai.streaming-chat-model.base-url}")
    private String baseUrl;
    @Value("${langchain4j.open-ai.streaming-chat-model.api-key}")
    private String apiKey;
    @Value("${langchain4j.open-ai.streaming-chat-model.model-name}")
    private String modelName;

    @Resource
    private ChatModel chatModel;

    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
        // 流式输出关闭思考模式，防止输出大段思考过程占满最大Token限制，导致生成的代码不完整，缺失CSS和JavaScript代码
        StreamingChatModel streamingChatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .modelName(modelName)
                .returnThinking(false)
                .customParameters(Map.of("thinking",Map.of("type","disabled")))
                .reasoningEffort("none")
                .build();
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .build();
    }
}

