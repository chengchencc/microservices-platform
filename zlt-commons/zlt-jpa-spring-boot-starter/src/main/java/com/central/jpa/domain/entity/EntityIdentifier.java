package com.central.jpa.domain.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * Description com.inspur.edp.epp.core.notification
 * Created by chengch
 * date on 2020/3/7
 */
public class EntityIdentifier implements Serializable {
    @Getter
    private Class type;
    @Getter
    public Object Id;

    public EntityIdentifier(Class type, Object id) {

        if (type == null){
            throw new IllegalArgumentException("type");
        }

        if (id == null){
            throw new IllegalArgumentException("id");
        }

        this.type = type;
        Id = id;
    }
}
