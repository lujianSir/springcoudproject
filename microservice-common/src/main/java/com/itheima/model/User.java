package com.itheima.model;


import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private int age;
    private String email;


    public User(){

    }

    public User(String name,String email){
        this.name=name;
        this.email=email;
    }

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;
}
