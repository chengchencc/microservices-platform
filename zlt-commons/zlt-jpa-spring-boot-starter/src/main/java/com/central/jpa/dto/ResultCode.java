package com.central.jpa.dto;

/**
 * @Author: zlt
 */
public enum ResultCode {
    SUCCESS(0),
    ERROR(1);

    private Integer code;
    ResultCode(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
