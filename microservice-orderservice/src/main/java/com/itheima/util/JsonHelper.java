package com.itheima.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper {

    //json转string
    public  static String getString(String str,String json){
        JsonParser jp = new JsonParser();
        //将json字符串转化成json对象
        JsonObject jo = jp.parse(json).getAsJsonObject();
        //获取message对应的值
        String message = jo.get(str).getAsString();
        return message ;
    }
}
