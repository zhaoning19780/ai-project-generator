package com.gpjy.ai.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StreamMessage class
 *
 * @author Ning
 * @date 2026/11/12
 * @Description: TODO 流式消息响应基类
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamMessage {
    private String type;
}
