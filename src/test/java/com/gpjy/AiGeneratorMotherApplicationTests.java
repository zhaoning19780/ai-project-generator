package com.gpjy;

import com.gpjy.ai.AiCodeGeneratorService;
import com.gpjy.core.AiCodeGeneratorFacade;
import com.gpjy.ai.model.entity.HtmlCodeResult;
import com.gpjy.ai.model.entity.MultiFileCodeResult;
import com.gpjy.ai.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class iGeneratorMotherApplicationTests {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateHtmlCode(){
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个程序员小白的工作记录工具");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode(){
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("做个程序员小白的留言板");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateAndSaveCode(){
        File file = aiCodeGeneratorFacade.generateAndSaveCode("生成一个登录页面", CodeGenTypeEnum.MULTI_FILE);
        Assertions.assertNotNull(file);
    }

}
