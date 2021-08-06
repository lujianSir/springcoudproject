package com.itheima.controller;

public enum Car {
    RED("红色"),BLACK("黑色");
    private String  color;
    Car(String p) {
        color = p;
    }
    String getColor() {
        return color;
    }
}
