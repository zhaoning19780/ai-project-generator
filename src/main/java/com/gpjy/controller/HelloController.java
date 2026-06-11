package com.gpjy.controller;

import com.gpjy.common.BaseResponse;
import com.gpjy.common.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController class
 *
 * @author Ning
 * @Description: TODO 简单测试基础通用代码
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(path = "/test",method = RequestMethod.GET)
    public BaseResponse<String> test(){
        return ResultUtils.success("hello world");
    }
}
