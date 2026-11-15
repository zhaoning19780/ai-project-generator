package com.gpjy.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.gpjy.ai.AiCodeGeneratorService;
import com.gpjy.ai.model.enums.CodeGenTypeEnum;
import com.gpjy.ai.tools.FileWriteTool;
import com.gpjy.exception.BusinessException;
import com.gpjy.exception.ErrorCode;
import com.gpjy.service.ChatHistoryService;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

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

    @Resource
    private StreamingChatModel reasoningStreamingChatModel;

    @Resource
    private StreamingChatModel openAiStreamingChatModel;

    /**
     * AI 服务实例缓存
     * 最大缓存1000个实例
     * 写入后30分钟过期
     * 访问后10分钟过期
     */
    private final Cache<String,AiCodeGeneratorService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .expireAfterAccess(Duration.ofMinutes(10))
            .removalListener((key,value,cause)->{
                log.debug("AI 服务实例被移除, appId: {}, 原因: {}",key,cause);
            }).build();

    /**
     * 根据appId获取服务(带缓存)这个方法是为了兼容历史逻辑
     * @param appId
     * @return
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
        return getAiCodeGeneratorService(appId,CodeGenTypeEnum.HTML);
    }

    /**
     * 根据appId和代码生成类型获取服务(带缓存)
     * @param appId
     * @return
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId,CodeGenTypeEnum codeGenTypeEnum) {
        String cacheKey = buildCacheKey(appId,codeGenTypeEnum);
        return serviceCache.get(cacheKey,key-> createAiCodeGeneratorService(appId,codeGenTypeEnum));
    }

    private String buildCacheKey(long appId,CodeGenTypeEnum codeGenTypeEnum){
        return appId + "_" + codeGenTypeEnum.getValue();
    }

    // 创建新的AI服务实例
    private AiCodeGeneratorService createAiCodeGeneratorService(long appId, CodeGenTypeEnum codeGenTypeEnum){
        log.info("为 appId: {} 创建新的AI服务实例",appId);

        // 根据appId构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        // 从数据库加载历史对话到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId,chatMemory,20);
        // 根据代码生成类型选择不同的模型配置
        return switch (codeGenTypeEnum){
            // Vue项目生成使用推理模型
            case VUE_PROJECT -> AiServices.builder(AiCodeGeneratorService.class)
                    .streamingChatModel(reasoningStreamingChatModel)
                    .chatMemoryProvider(memoryId -> chatMemory)
                    .tools(new FileWriteTool())
                    .hallucinatedToolNameStrategy(toolExecutionRequest -> ToolExecutionResultMessage.from(
                            toolExecutionRequest,"Error: there is no tool called" + toolExecutionRequest.name()
                    )).build();
            // HTML和多文件生成使用默认模型
            case HTML,MULTI_FILE -> AiServices.builder(AiCodeGeneratorService.class)
                    .chatModel(chatModel)
                    .streamingChatModel(openAiStreamingChatModel)
                    .chatMemory(chatMemory)
                    .build();
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持的代码生成类型: " +codeGenTypeEnum.getValue());
        };
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

