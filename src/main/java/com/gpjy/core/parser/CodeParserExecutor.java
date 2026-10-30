package com.gpjy.core.parser;

import com.gpjy.ai.model.enums.CodeGenTypeEnum;
import com.gpjy.exception.BusinessException;
import com.gpjy.exception.ErrorCode;

/**
 * CodeParserExecutor class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO 代码解析执行器
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    /**
     * 执行代码解析
     * @param codeContent
     * @param codeGenTypeEnum
     * @return
     */
    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenTypeEnum){
        return switch(codeGenTypeEnum){
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持的代码生成类型: " + codeGenTypeEnum);
        };
    }
}
