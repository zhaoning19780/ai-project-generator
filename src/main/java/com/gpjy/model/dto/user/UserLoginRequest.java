package com.gpjy.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * UserLoginRequest class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO 用户登录接收请求参数类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户账号
    private String userAccount;

    // 用户密码
    private String userPassword;
}
