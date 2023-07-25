package com.central.jpa.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * JSON 工具封装，
 * 目前使用fastjson，都是用这个封装的话，后期支持统一替换为Gson等其他json序列化工具
 * Created by chengch
 * date on 2020/2/12
 */
public class JsonConvert {

    public static String toJSONString(Object obj){

        return JSON.toJSONString(obj);
    }

    public static JSONObject toJSONObject(String text){
        return JSON.parseObject(text);
    }

    public static JSONArray toJSONArray(String text){
        return JSON.parseArray(text);
    }

    public static Object toObject(String text){
        return JSON.parse(text);
    }

    public static boolean isValid(String text){
        return JSON.isValid(text);
    }

    public static boolean isValidArray(String text){
        return JSON.isValidArray(text);
    }

    public static boolean isValidObject(String text){
        return JSON.isValidObject(text);
    }


    public static <T> T toObject(String text, Class<T> classType){
        return JSON.parseObject(text,classType);
    }

    public static <T> T toObject(String text, JsonTypeReference<T> type, Feature... features) {
        return JSON.parseObject(text,type,features);
    }

}
