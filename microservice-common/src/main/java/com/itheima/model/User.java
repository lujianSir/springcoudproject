package com.itheima.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.Table;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
