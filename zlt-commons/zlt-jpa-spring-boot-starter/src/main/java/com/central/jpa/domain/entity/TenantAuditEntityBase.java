package com.central.jpa.domain.entity;

import com.central.jpa.tenancy.TenantConsts;
import com.central.jpa.tenancy.TenantEntityListener;
import com.central.jpa.tenancy.TenantSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */

//@EqualsAndHashCode(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(value = {AuditEntityListener.class, TenantEntityListener.class})
@FilterDef(name= TenantConsts.Tenant_Filter_Name, parameters={@ParamDef(name=TenantConsts.Tenant_Filter_Param_Name, type="string")})
@Filters({@Filter(name=TenantConsts.Tenant_Filter_Name, condition=TenantConsts.Tenant_Filter_Field_Name +" = :"+TenantConsts.Tenant_Filter_Param_Name)})
public class TenantAuditEntityBase<T extends Serializable> extends EntityBase<T> implements TenantSupport {

    public static final String DELETE_TIME = "deletion_time";
    public static final String DELETE_USER_ID = "deletion_user_id";
    public static final String IS_DELETE = "is_delete";

    @Column
    protected Date creationTime;
    @Column(length = 36)
    protected String creationUserId;
    @Column
    protected Date lastModifyTime;
    @Column(length = 36)
    protected String lastModifyUserId;
    @Column(name = "deletion_time")
    protected Date deletionTime;
    @Column(name = "deletion_user_id",length = 36)
    protected String deletionUserId;
    @Column(name = "is_delete",nullable = false)
    private Boolean isDelete = false;

    @Column(name = TenantConsts.Tenant_Filter_Field_Name)
    protected String tenantId;
}

