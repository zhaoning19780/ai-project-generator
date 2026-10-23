package com.gpjy.model.dto.user;

import com.gpjy.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * UserQueryRequest class
 *
 * @author Ning
 * @date 2026/10/20
 * @Description: TODO 用户查询请求
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户ID
    private Long id;

    // 用户账号
    private String userAccount;

    // 用户昵称
    private String userName;

    // 用户头像
    private String userAvatar;

    // 用户简介
    private String userProfile;

    // 用户角色
    private String userRole;
}
