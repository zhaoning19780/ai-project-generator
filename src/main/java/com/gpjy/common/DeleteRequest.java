package com.gpjy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * DeleteRequest class
 *
 * @author Ning
 * @Description: TODO 删除请求类，封装删除请求参数。
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
