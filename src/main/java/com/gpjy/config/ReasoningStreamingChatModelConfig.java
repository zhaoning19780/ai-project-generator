package com.gpjy.config;

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;

/**
 * ReasoningStreamingChatModelConfig class
 *
 * @author Ning
 * @date 2026/11/12
 * @Description: TODO 流式推理模型配置
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Configuration
@ConfigurationProperties(prefix = "langchain4j.open-ai.chat-model")
@Data
public class ReasoningStreamingChatModelConfig {

    private String baseUrl;

    private String apiKey;

    @Bean
    public StreamingChatModel reasoningStreamingChatModel(){
        // 关闭思考过程
        final String modelName = "deepseek-v4-pro";
        final int maxTokens = 32768;
        return OpenAiStreamingChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .maxTokens(maxTokens)
                .returnThinking(false)
                .customParameters(Map.of("thinking",Map.of("type","disabled")))
                .reasoningEffort("none")
                .logRequests(true)
                .logResponses(true)
                .timeout(Duration.ofSeconds(60))
                .build();
    }
}
