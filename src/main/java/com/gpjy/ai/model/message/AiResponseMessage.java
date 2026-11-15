package com.gpjy.ai.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * AiResponseMessage class
 *
 * @author Ning
 * @date 2026/11/12
 * @Description: TODO AI响应消息
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AiResponseMessage extends StreamMessage{

    private String data;

    public AiResponseMessage(String data) {
        super(StreamMessageTypeEnum.AI_RESPONSE.getValue());
        this.data = data;
    }
}
