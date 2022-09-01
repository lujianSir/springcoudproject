package com.itheima.controller;

import java.util.Random;

/**
 * 学习建造者模式
 */
public class Test {

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

    //必选构建属性
    public Test(Builder builder){
        this.stuName=builder.stuName;
        this.height=builder.height;
        this.stuAge=builder.stuAge;
        this.weight=builder.weight;
    }

    //建造者类
    public static class Builder{

        private String stuName;
        private Integer stuAge;
        private double height;
        private double weight;

        public Builder(){

        }

        //将属性作为步骤
        public Builder setStuName(String  stuName){
            this.stuName=stuName;
            return this;
        }

        public Builder setStuAge(Integer stuAge){
            this.stuAge=stuAge;
            return this;
        }

        public Builder setHeight(double height){
            this.height=height;
            return this;
        }

        public Builder setWeight(double weight){
            this.weight=weight;
            return this;
        }

        //定义build方法最终返回，属性步骤后的结果
        public Test  build(){
            return new Test(this);
        }
    }

}
