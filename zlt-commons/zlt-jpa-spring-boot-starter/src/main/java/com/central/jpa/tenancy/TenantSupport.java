package com.central.jpa.tenancy;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/23
 */

public interface TenantSupport {
    String getTenantId();
    void setTenantId(String tenantId);
}
