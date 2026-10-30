package com.gpjy.ai.model.entity;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * MultiFileCodeResult class
 *
 * @author Ning
 * @date 2026/10/24
 * @Description: TODO 封装多文件代码结果
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Description("生成多个代码文件的结果")
@Data
public class MultiFileCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("CSS代码")
    private String cssCode;

    @Description("JS代码")
    private String jsCode;

    @Description("生成代码的描述")
    private String description;
}
