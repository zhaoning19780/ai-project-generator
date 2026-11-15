package com.gpjy.core.handler;

import com.gpjy.model.entity.User;
import com.gpjy.model.enums.ChatHistoryMessageTypeEnum;
import com.gpjy.service.ChatHistoryService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * SimpleTextStreamHandler class
 *
 * @author Ning
 * @date 2026/11/12
 * @Description: TODO 原生文本流处理器
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Slf4j
public class SimpleTextStreamHandler {
    /**
     * 处理传统流(HTML、MULTI_FILE)
     * 直接收集完整的文本响应
     * @param originFlux 原始流
     * @param chatHistoryService 聊天历史服务
     * @param appId 应用ID
     * @param loginUser 登录用户
     * @return
     */
    public Flux<String> handle(Flux<String> originFlux, ChatHistoryService chatHistoryService, long appId, User loginUser){
        StringBuilder aiResponseBuilder = new StringBuilder();
        return originFlux.map(chunk -> {
            // 收集AI响应内容
            aiResponseBuilder.append(chunk);
            return chunk;
        }).doOnComplete(()->{
            // 流式响应完成后，添加AI消息到对话历史
            String aiResponse = aiResponseBuilder.toString();
            chatHistoryService.addChatMessage(appId,aiResponse, ChatHistoryMessageTypeEnum.AI.getValue(), loginUser.getId());
        }).doOnError(error -> {
            // 如果AI回复失败，也要记录错误消息
            String errorMessage = "AI回复失败: " + error.getMessage();
            chatHistoryService.addChatMessage(appId,errorMessage,ChatHistoryMessageTypeEnum.AI.getValue(), loginUser.getId());
        });
    }
}
