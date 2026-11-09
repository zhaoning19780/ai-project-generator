package com.gpjy.service;

import com.gpjy.model.dto.chathistory.ChatHistoryQueryRequest;
import com.gpjy.model.entity.ChatHistory;
import com.gpjy.model.entity.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author Ning
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    boolean addChatMessage(Long appId,String message,String messageType,Long userId);

    boolean deleteByAppId(Long appId);

    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);

    int loadChatHistoryToMemory(long appId, MessageWindowChatMemory chatMemory,int maxCount);
}
