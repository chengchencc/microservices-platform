package com.central.jpa;

/**
 * Description com.inspur.edp.epp.infrastructure
 * Created by chengch
 * date on 2020/3/6
 */
public interface ISession {
    String getUserId();
    String getUserName();
    String getUserCode();
    String getTenantId();
}
