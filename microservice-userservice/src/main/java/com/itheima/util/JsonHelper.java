package com.itheima.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dss
 * @Date 2020/8/1 10:19
 * @Version 1.0
 */
public class JsonHelper {

    private static ObjectMapper om = new ObjectMapper();

    static{
        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 设置Date类型的序列化及反序列化格式
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象序列化成json
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String toJson(T obj) throws Exception {
        String json = om.writeValueAsString(obj);
        return json;
    }

    /**
     * 通过json返回一个对象
     * @param json
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T>T getObject(String json,Class<T> obj) throws Exception{
        T ob = om.readValue(json.getBytes("UTF-8"), obj);
        return ob;
    }

    /**
     * 通过key值返回一个对象
     * @param key
     * @param json
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T>T getKeyObject(String key,String json,Class<T> obj) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        String str = om.writeValueAsString(dataNode);
        T ob = om.readValue(str.getBytes("UTF-8"), obj);
        return ob;
    }

    /**
     * 通过json返回一个List对象
     * @param json
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T>  getObjectList(String json, Class<T> obj) throws Exception {
        CollectionType javaType = om.getTypeFactory().constructCollectionType(List.class,obj);
        List<T> personList = om.readValue(json.getBytes("UTF-8"), javaType);
        return personList;
    }

    /**
     * 通过key返回一个list对象
     * @param json
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T>  getKeyObjectList(String key,String json, Class<T> obj) throws Exception {
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        String str = om.writeValueAsString(dataNode);
        CollectionType javaType = om.getTypeFactory().constructCollectionType(List.class,obj);
        List<T> personList = om.readValue(str.getBytes("UTF-8"), javaType);
        return personList;
    }

    /**
     * 通过key取Boolean类型的值
     * @param key
     * @return
     */
    public static Boolean getBoolean(String key,String json) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        return dataNode.asBoolean();
    }

    /**
     * 通过key取Double类型的值
     * @param key
     * @param json
     * @return
     */
    public static Double getDouble(String key,String json) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        return dataNode.asDouble();
    }

    /**
     * 通过key取Long类型的值
     * @param key
     * @return
     */
    public static Long getLong(String key,String json) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        return dataNode.asLong();
    }

    /**
     * 通过key取String类型的值
     * @param key
     * @return
     */
    public static String getString(String key,String json) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        return dataNode.textValue();
    }

    /**
     * 通过key取int类型的值
     * @param key
     * @param json
     * @return
     * @throws Exception
     */
    public static int getInt(String key,String json) throws Exception{
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        return dataNode.asInt();
    }

    /**
     * 通过key获取一个List<Integer>
     * @param key
     * @param json
     * @return
     * @throws Exception
     */
    public static List<Integer> getKetIntList(String key,String json) throws Exception {
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        ArrayNode arrayNode = (ArrayNode) node.get(key);
        return getIntegers(arrayNode);
    }

    /**
     * 通过key获取一个List<String>
     * @param key
     * @param json
     * @return
     * @throws Exception
     */
    public static List<String> getKeyStringList(String key,String json) throws Exception {
        JsonNode node = om.readTree(json);
        JsonNode dataNode = node.get(key);
        ArrayNode arrayNode = (ArrayNode) node.get(key);
        return getStrings(arrayNode);
    }

    /**
     * 通过json获取一个List<String>
     * @param json
     * @return
     * @throws Exception
     */
    public static List<String> getStringList(String json) throws Exception {
        JsonNode node = om.readTree(json);
        ArrayNode arrayNode = (ArrayNode) node;
        return getStrings(arrayNode);
    }

    private static List<String> getStrings(ArrayNode arrayNode) throws JsonProcessingException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < arrayNode.size(); i++) {
            String str = om.writeValueAsString(arrayNode.get(i));
            list.add(str);
        }
        return list;
    }

    /**
     * 通过json获取一个List<Integer>
     * @param json
     * @return
     * @throws Exception
     */
    public static List<Integer> getIntList(String json) throws Exception {
        JsonNode node = om.readTree(json);
        ArrayNode arrayNode = (ArrayNode) node;
        return getIntegers(arrayNode);
    }

    private static List<Integer> getIntegers(ArrayNode arrayNode) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arrayNode.size(); i++) {
            Integer o = arrayNode.get(i).asInt();
            list.add(o);
        }
        return list;
    }
}
