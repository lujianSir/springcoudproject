package com.itheima.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Integer id;

    private int number;

    private String userid;

    private Date createtime;
}