package com.gpjy.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * AppAdminUpdateRequest class
 *
 * @author Ning
 * @date 2026/10/30
 * @Description: TODO 管理员更新应用请求
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class AppAdminUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用封面
     */
    private String cover;

    /**
     * 优先级
     */
    private Integer priority;
}
