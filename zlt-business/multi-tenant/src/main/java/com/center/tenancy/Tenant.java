package com.center.tenancy;

import com.central.jpa.domain.entity.AuditEntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Tenant extends AuditEntityBase<String> {
    private String name;
    private String code;
    private String url;
    private String userName;
    private String password;
    private String dbType;

}
