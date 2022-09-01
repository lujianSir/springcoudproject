package com.itheima.controller;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Test1 {

    /**
     * 名称 必须
     */
    private String stuName;

    /**
     * 年纪  非必须
     */
    private Integer stuAge;

    /**
     * 高度 非必须
     */
    private double height;

    /**
     * 重量 非必须
     */
    private double weight;
}
