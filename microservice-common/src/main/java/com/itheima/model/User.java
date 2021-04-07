package com.itheima.model;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private Integer age;
	private List<Order> orderList;
}