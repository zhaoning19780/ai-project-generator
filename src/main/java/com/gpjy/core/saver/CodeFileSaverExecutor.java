package com.gpjy.core.saver;

import com.gpjy.ai.model.entity.HtmlCodeResult;
import com.gpjy.ai.model.entity.MultiFileCodeResult;
import com.gpjy.ai.model.enums.CodeGenTypeEnum;
import com.gpjy.exception.BusinessException;
import com.gpjy.exception.ErrorCode;

import java.io.File;

/**
 * CodeFileSaverExecutor class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO 代码文件保存执行器
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public class CodeFileSaverExecutor {

    private static final HtmlCodeFileSaverTemplate htmlCodeFileSaver = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaver = new MultiFileCodeFileSaverTemplate();

    /**
     * 执行代码保存
     * @param codeResult
     * @param codeGenTypeEnum
     * @return
     */
    public static File executeSaver(Object codeResult, CodeGenTypeEnum codeGenTypeEnum){
        return switch(codeGenTypeEnum){
            case HTML -> htmlCodeFileSaver.saveCode((HtmlCodeResult) codeResult);
            case MULTI_FILE -> multiFileCodeFileSaver.saveCode((MultiFileCodeResult) codeResult);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型: " + codeGenTypeEnum);
        };
    }
}
