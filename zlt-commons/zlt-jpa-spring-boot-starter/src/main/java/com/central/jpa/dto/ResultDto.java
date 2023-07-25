package com.central.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zlt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> implements Serializable {

    private T result;
    private Integer code;
    private String message;

    public static <T> ResultDto<T> succeed(String msg) {
        return of(null, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDto<T> succeed(T model, String msg) {
        return of(model, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDto<T> succeed(T model) {
        return of(model, ResultCode.SUCCESS.getCode(), "");
    }

    public static <T> ResultDto<T> of(T data, Integer code, String msg) {
        return new ResultDto<>(data, code, msg);
    }

    public static <T> ResultDto<T> failed(String msg) {
        return of(null, ResultCode.ERROR.getCode(), msg);
    }

    public static <T> ResultDto<T> failed(T model, String msg) {
        return of(model, ResultCode.ERROR.getCode(), msg);
    }
}
