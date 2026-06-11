package com.gpjy.exception;

import lombok.Getter;

/**
 * BusinessException class
 *
 * @author Ning
 * @Description: TODO 自定义异常类，用于封装业务异常信息，包含错误码和错误消息。
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
