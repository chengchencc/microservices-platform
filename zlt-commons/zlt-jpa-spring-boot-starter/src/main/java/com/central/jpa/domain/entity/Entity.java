package com.central.jpa.domain.entity;

/**
 * Description com.inspur.edp.epp.infrastructure.domain.entities
 * Created by chengch
 * date on 2020/1/3
 */
public interface Entity<T> {
    public T getId();
    public void setId(T id);
}
