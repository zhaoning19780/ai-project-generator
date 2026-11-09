package com.gpjy.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.gpjy.ai.AiCodeGeneratorService;
import com.gpjy.service.ChatHistoryService;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
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
@Slf4j
public class AiCodeGeneratorServiceFactory {

    @Value("${langchain4j.open-ai.streaming-chat-model.base-url}")
    private String baseUrl;
    @Value("${langchain4j.open-ai.streaming-chat-model.api-key}")
    private String apiKey;
    @Value("${langchain4j.open-ai.streaming-chat-model.model-name}")
    private String modelName;

    @Resource
    private ChatModel chatModel;

    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Resource
    private ChatHistoryService chatHistoryService;

    /**
     * AI 服务实例缓存
     * 最大缓存1000个实例
     * 写入后30分钟过期
     * 访问后10分钟过期
     */
    private final Cache<Long,AiCodeGeneratorService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .expireAfterAccess(Duration.ofMinutes(10))
            .removalListener((key,value,cause)->{
                log.debug("AI 服务实例被移除, appId: {}, 原因: {}",key,cause);
            }).build();

    /**
     * 根据appId获取服务(带缓存)
     * @param appId
     * @return
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
        return serviceCache.get(appId,this::createAiCodeGeneratorService);
    }

    // 创建新的AI服务实例
    private AiCodeGeneratorService createAiCodeGeneratorService(long appId){
        // 流式输出关闭思考模式，防止输出大段思考过程占满最大Token限制，导致生成的代码不完整，缺失CSS和JavaScript代码
        StreamingChatModel streamingChatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .modelName(modelName)
                .returnThinking(false)
                .customParameters(Map.of("thinking",Map.of("type","disabled")))
                .reasoningEffort("none")
                .build();

        log.info("为 appId: {} 创建新的AI服务实例",appId);

        // 根据appId构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        // 从数据库加载历史对话到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId,chatMemory,20);
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();
    }

    /**
     * 默认提供一个Bean
     * @return
     */
    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService(){
        return getAiCodeGeneratorService(0L);
    }
}

