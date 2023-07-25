package com.central.jpa.domain.entity;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
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
public class GenerateSnowflakeId extends UUIDGenerator {
    //TODO:需改为从配置文件读取
    private final static Snowflake snowflake = IdUtil.getSnowflake(0, 0);
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String id = ((Entity<String>)object).getId();
        if (!StringUtils.isBlank(id) ) {
            return id;
        }
        return snowflake.nextIdStr();
    }
}
