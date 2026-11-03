package com.gpjy.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * AppDeployRequest class
 *
 * @author Ning
 * @date 2026/11/3
 * @Description: TODO
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class AppDeployRequest implements Serializable {

    /**
     * 应用 id
     */
    private Long appId;

    private static final long serialVersionUID = 1L;
}