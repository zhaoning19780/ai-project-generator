package com.gpjy.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * LoginUserVO class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO 用户登录接口返回给前端数据类
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户id
    private Long id;
    // 账号
    private String userAccount;
    // 用户昵称
    private String userName;
    // 用户头像
    private String userAvatar;
    // 用户简介
    private String userProfile;
    // 用户角色
    private String userRole;
    // 创建时间
    private LocalDateTime createTime;
    // 更新时间
    private LocalDateTime updateTime;
}
