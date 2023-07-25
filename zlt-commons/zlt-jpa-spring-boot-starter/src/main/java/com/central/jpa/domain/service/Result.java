package com.central.jpa.domain.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lvdongwei
 * @date 2020/4/6
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 处理结果
     */
    private String message = "成功";

    /**
     * 状态码
     */
    private Integer code = 0;

    /**
     * 数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {
    }

    public Result<T> success(String message) {
        this.success = true;
        this.message = message;
        this.code = 200;
        return this;
    }

    /**
     * 处理无返回结果
     *
     * @return
     */
    public static Result<Object> ok() {
        Result<Object> result = new Result<Object>();
        result.setSuccess(true);
        result.setMessage("成功");
        result.setCode(200);
        return result;
    }

    /**
     * 处理无返回结果
     *
     * @param msg
     * @return
     */
    public static Result<Object> ok(String msg) {
        Result<Object> result = new Result<Object>();
        result.setSuccess(true);
        result.setMessage(msg);
        result.setCode(200);
        return result;
    }

    /**
     * 处理有返回结果
     *
     * @param data
     * @return
     */
    public static Result<Object> ok(Object data) {
        Result<Object> result = new Result<Object>();
        result.setSuccess(true);
        result.setMessage("成功");
        result.setCode(200);
        result.setResult(data);
        return result;
    }


    public static Result<Object> error(String msg) {
        return error(500, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }

    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noAuth(String msg) {
        return error(510, msg);
    }
}
