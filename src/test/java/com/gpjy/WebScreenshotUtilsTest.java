package com.gpjy;

import com.gpjy.utils.WebScreenshotUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * WebScreenshotUtilsTest class
 *
 * @author Ning
 * @date 2026/11/15
 * @Description: TODO
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Slf4j
@SpringBootTest
public class WebScreenshotUtilsTest {

    @Test
    void saveWebPageScreenshot() {
        String testUrl = "https://www.codefather.cn";
        String webPageScreenshot = WebScreenshotUtils.saveWebPageScreenshot(testUrl);
        Assertions.assertNotNull(webPageScreenshot);
    }
}
