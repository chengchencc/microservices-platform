package com.central.jpa.domain.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * id为null或者空，才去生成uuid
 * Created by chengch
 * date on 2020/1/3
 */
public class EppUUIDGeneratorIfNullThenGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String id = ((Entity<String>)object).getId();
        if (! StringUtils.isBlank(id) ) {
            return id;
        }
        return super.generate(session, object);
    }
}
