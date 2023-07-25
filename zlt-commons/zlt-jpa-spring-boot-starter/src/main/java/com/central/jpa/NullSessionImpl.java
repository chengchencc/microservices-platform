package com.central.jpa;

import org.springframework.stereotype.Component;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:36
 **/
@Component
public class NullSessionImpl implements ISession {
    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getUserCode() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }
}
