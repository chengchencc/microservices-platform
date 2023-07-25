package com.central.jpa.tenancy;

import lombok.Data;

public interface TenantDetails {

    public String getName();

    public String getCode();

    public String getUrl();

    public String getUserName();

    public String getPassword();

    public String getDriverClassName();
}
