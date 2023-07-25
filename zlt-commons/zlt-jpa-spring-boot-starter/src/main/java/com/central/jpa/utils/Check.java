package com.central.jpa.utils;

/**
 * Description com.inspur.edp.epp.infrastructure.utils
 * Created by chengch
 * date on 2020/3/21
 */
public class Check {


    public static <T> T notNull(T value){
        if (value == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        return value;
    }

    public static <T> T notNull(T value, String parameterName){
        if (value == null){
            throw new IllegalArgumentException("参数 "+parameterName+" 不能为空！");
        }
        return value;
    }


}
