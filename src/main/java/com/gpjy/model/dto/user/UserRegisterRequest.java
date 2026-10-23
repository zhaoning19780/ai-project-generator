package com.gpjy.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * UserRegisterRequest class
 *
 * @author Ning
 * @date 2026/10/19
 * @Description: TODO 接收用户注册请求参数类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 账号
    private String userAccount;

    // 密码
    private String userPassword;

    // 确认密码
    private String checkPassword;

}
