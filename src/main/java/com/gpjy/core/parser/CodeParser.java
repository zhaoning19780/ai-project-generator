package com.gpjy.core.parser;

/**
 * CodeParser class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO 代码解析器策略接口
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public interface CodeParser<T> {

    /**
     * 解析代码内容
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
