package com.itheima.common;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Tool {

    /**
     * 对象为null
     * @param o 对象
     * @return true 正确  false 错误
     */
    public boolean isEmptyObject(Object o){
        if(o==null){
            return true;
        }
        return false;
    }

    /**
     * 对象不为null
     * @param o 对象
     * @return true 正确  false 错误
     */
    public boolean isNotEmptyObject(Object o){
        if(o!=null){
            return true;
        }
        return false;
    }

    /**
     * 判断list是为null或者size()==0
     * @param list 集合
     * @return true 正确  false 错误
     */
    public boolean isEmptyList(List<?> list){
        if(list==null || list.size()==0){
            return true;
        }
        return false;
    }

    /**
     * 判断list不为null或者size()》0
     * @param list 集合
     * @return true 正确  false 错误
     */
    public boolean isNotEmptyList(List<?> list){
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 判断map 为null 或者size()==0
     * @param map 集合
     * @return true 正确  false 错误
     */
    public boolean isEmptyMap(Map<?,?> map){
        if(map==null || map.size()==0){
            return true;
        }
        return false;
    }

    /**
     * 判断是否map 为null 或者size()==0 或不包含参数
     * @param map 集合
     * @param o 对象
     * @return
     */
    public boolean isEmptyMap(Map<?,?> map,Object o){
        if(map==null || map.size()==0){
            return true;
        }
        if(!map.containsKey(o)){
            return true;
        }
        return false;
    }

    /**
     * 判断map不为null 和size()>0
     * @param map 集合
     * @return
     */
    public boolean isNotEmptyMap(Map<?,?> map){
        if(map!=null && map.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 判断map不为null 和size()>0 和包含参数
     * @param map 集合
     * @param o 参数
     * @return
     */
    public boolean isNotEmptyMap(Map<?,?> map,Object o){
        if(map==null || map.size()==0){
            return false;
        }
        if(map.containsKey(o)){
            return true;
        }
        return false;
    }

}
