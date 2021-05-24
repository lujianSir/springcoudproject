package com.itheima.model;


import lombok.Data;

import javax.persistence.Table;

@Data
public class User {
    private Long id;
    private String name;
    private int age;
    private String email;
}
