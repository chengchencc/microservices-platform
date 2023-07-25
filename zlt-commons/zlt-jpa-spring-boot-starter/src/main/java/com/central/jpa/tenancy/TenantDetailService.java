package com.central.jpa.tenancy;

public interface TenantDetailService {
    TenantDetails get(String tenantId);
}
