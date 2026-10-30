package com.gpjy.core.saver;

import cn.hutool.core.util.StrUtil;
import com.gpjy.ai.model.entity.HtmlCodeResult;
import com.gpjy.ai.model.enums.CodeGenTypeEnum;
import com.gpjy.exception.BusinessException;
import com.gpjy.exception.ErrorCode;

/**
 * HtmlCodeFileSaverTemplate class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO HTML代码文件保存器
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult>{

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
