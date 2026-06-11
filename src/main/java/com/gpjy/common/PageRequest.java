package com.gpjy.common;

import lombok.Data;

/**
 * PageRequest class
 *
 * @author Ning
 * @Description: TODO 分页请求参数类，封装分页请求参数，包括当前页号、页面大小、排序字段和排序顺序。
 * @Saying: 又是学习Java痛苦的一天，只要学不死，就往死里学吧！
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}
