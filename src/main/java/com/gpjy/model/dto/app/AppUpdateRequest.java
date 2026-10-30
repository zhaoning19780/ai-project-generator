package com.gpjy.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * AppUpdateRequest class
 *
 * @author Ning
 * @date 2026/10/30
 * @Description: TODO 更新应用请求类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class AppUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;
}
