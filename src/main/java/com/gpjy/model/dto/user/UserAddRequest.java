package com.gpjy.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * UserAddRequest class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO 创建用户请求类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class UserAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户昵称
    private String userName;

    // 用户账号
    private String userAccount;

    // 用户头像
    private String userAvatar;

    // 用户简介
    private String userProfile;

    // 用户角色
    private String userRole;
}
