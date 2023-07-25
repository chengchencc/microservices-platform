package com.central.jpa.jpa;

import com.central.jpa.exception.BusinessException;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:48
 **/
public class JpaConvertException extends BusinessException {

    public JpaConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
