package com.gpjy.core.parser;

import com.gpjy.ai.model.entity.MultiFileCodeResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MultiFileCodeParser class
 *
 * @author Ning
 * @date 2026/10/28
 * @Description: TODO 多文件代码解析器
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
public class MultiFileCodeParser implements CodeParser<MultiFileCodeResult>{

    private static final Pattern HTML_CODE_PATTERN = Pattern.compile("```html\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);
    private static final Pattern CSS_CODE_PATTERN = Pattern.compile("```css\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);
    private static final Pattern JS_CODE_PATTERN = Pattern.compile("```(?:js|javascript)\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);

    @Override
    public MultiFileCodeResult parseCode(String codeContent) {
        MultiFileCodeResult result = new MultiFileCodeResult();
        // 提取各类代码
        String htmlCode = extractCodeByPattern(codeContent, HTML_CODE_PATTERN);
        String cssCode = extractCodeByPattern(codeContent,CSS_CODE_PATTERN);
        String jsCode = extractCodeByPattern(codeContent,JS_CODE_PATTERN);
        // 设置HTML代码
        if(htmlCode != null && !htmlCode.trim().isEmpty()){
            result.setHtmlCode(htmlCode.trim());
        }
        // 设置CSS代码
        if(cssCode != null && !cssCode.trim().isEmpty()){
            result.setCssCode(cssCode.trim());
        }
        // 设置JS代码
        if(jsCode != null && !jsCode.trim().isEmpty()){
            result.setJsCode(jsCode.trim());
        }
        return result;
    }

    private String extractCodeByPattern(String content, Pattern pattern){
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
