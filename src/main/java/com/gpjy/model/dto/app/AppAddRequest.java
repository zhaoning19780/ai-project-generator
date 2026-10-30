package com.gpjy.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * AppAddRequest class
 *
 * @author Ning
 * @date 2026/10/30
 * @Description: TODO 创建应用请求类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class AppAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用初始化的 prompt
     */
    private String initPrompt;
}
