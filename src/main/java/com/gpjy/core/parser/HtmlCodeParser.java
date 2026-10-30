package com.gpjy.core.parser;

import com.gpjy.ai.model.entity.HtmlCodeResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HtmlCodeParser class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO HTML单文件代码解析器
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public class HtmlCodeParser implements CodeParser<HtmlCodeResult>{

    private static final Pattern HTML_CODE_PATTERN = Pattern.compile("```html\\s*\\n([\\s\\S]*?)```",
            Pattern.CASE_INSENSITIVE);
    @Override
    public HtmlCodeResult parseCode(String codeContent) {
        HtmlCodeResult result = new HtmlCodeResult();
        // 提取HTML代码
        String htmlCode = extractHtmlCode(codeContent);
        if(htmlCode != null && !htmlCode.trim().isEmpty()){
            result.setHtmlCode(htmlCode.trim());
        }else{
            // 如果没有找到代码块，将整个内容作为HTML
            result.setHtmlCode(codeContent.trim());
        }
        return result;
    }

    /**
     * 提取HTML代码内容
     * @param content 原始内容
     * @return HTML代码
     */
    private String extractHtmlCode(String content){
        Matcher matcher = HTML_CODE_PATTERN.matcher(content);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
