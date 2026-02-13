package com.central.system.tenancy;

import com.central.jpa.dto.EntityDto;
import com.central.jpa.tenancy.TenantDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TenantInfoDto extends EntityDto<String> implements TenantDetails {
    private String name;
    private String code;
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
}
