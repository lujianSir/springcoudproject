package com.itheima.model;

import java.io.IOException;
import java.io.Serializable;

public class RestMessage<T>  implements Serializable {

    private static final long serialVersionUID = -1865510446859810360L;
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("消息对象")
    private String message;
    @ApiModelProperty("消息代码")
    private String code;
    @ApiModelProperty("返回对象")
    private T data;

    public RestMessage() {
    }

    public static <T> RestMessage<T> newInstance(boolean success, String message, T data) {
        return new RestMessage(success, message, data);
    }

    public static <T> RestMessage<T> newInstance(boolean success, String code, String message, T data) {
        return new RestMessage(success, code, message, data);
    }

    public RestMessage(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = "200";
    }

    public RestMessage(boolean success, String code, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) throws IOException {
    }
}
