package com.gpjy.ai.model.entity;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * HtmlCodeResult class
 *
 * @author Ning
 * @date 2026/10/24
 * @Description: TODO 封装AI返回的内容
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
@Description("生成 HTML 代码文件的结果")
public class HtmlCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("生成代码的描述")
    private String description;
}
