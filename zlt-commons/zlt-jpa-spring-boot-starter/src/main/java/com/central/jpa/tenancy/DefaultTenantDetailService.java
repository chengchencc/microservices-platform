package com.central.jpa.tenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

public class DefaultTenantDetailService implements TenantDetailService {
    private final DataSourceProperties dataSourceProperties;
    public DefaultTenantDetailService(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public TenantDetails get(String tenantId) {
        return new TenantDetails() {
            @Override
            public String getName() {
                return dataSourceProperties.getName();
            }

            @Override
            public String getCode() {
                return dataSourceProperties.getName();
            }

            @Override
            public String getUrl() {
                return dataSourceProperties.getUrl();
            }

            @Override
            public String getUserName() {
                return dataSourceProperties.getName();
            }

            @Override
            public String getPassword() {
                return dataSourceProperties.getPassword();
            }

            @Override
            public String getDriverClassName() {
                return dataSourceProperties.getDriverClassName();
            }
        };
    }
}
