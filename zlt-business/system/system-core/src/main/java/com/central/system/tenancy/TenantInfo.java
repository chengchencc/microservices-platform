package com.central.system.tenancy;

import com.central.jpa.domain.entity.AuditEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class TenantInfo extends AuditEntityBase<String> {
    private String name;
    private String code;
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
}
