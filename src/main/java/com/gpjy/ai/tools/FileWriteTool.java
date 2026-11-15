package com.gpjy.ai.tools;

import com.gpjy.constant.AppConstant;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * FileWriteTool class
 *
 * @author Ning
 * @date 2026/11/12
 * @Description: TODO 文件写入工具。AI通过工具调用方式写入文件
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Slf4j
public class FileWriteTool {

    @Tool("写入文件到指定路径")
    public String writeFile(@P("文件的相对路径") String relativeFilePath, @P("要写入的文件内容") String content, @ToolMemoryId Long appId){
        try{
            Path path = Paths.get(relativeFilePath);
            if(!path.isAbsolute()){
                // 相对路径处理，创建基于appId的项目目录
                String projectName = "vue_project_" + appId;
                Path projectRoot = Paths.get(AppConstant.CODE_OUTPUT_ROOT_DIR,projectName);
                path = projectRoot.resolve(relativeFilePath);
            }
            // 创建父目录(如果不存在)
            Path parentDir = path.getParent();
            if(parentDir != null){
                Files.createDirectories(parentDir);
            }
            // 写入文件内容
            Files.write(path,content.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
            log.info("成功写入文件: {}",path.toAbsolutePath());
            // 注意要返回相对路径，不能将绝对路径返回
            return "文件写入成功: " + relativeFilePath;
        } catch (Exception e) {
            String errorMessage = "文件写入失败: " + relativeFilePath + ", 错误: " + e.getMessage();
            log.error(errorMessage,e);
            return errorMessage;
        }
    }
}
