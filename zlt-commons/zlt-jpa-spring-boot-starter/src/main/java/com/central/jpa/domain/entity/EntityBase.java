package com.central.jpa.domain.entity;

import com.central.jpa.tenancy.TenantConsts;
import com.central.jpa.tenancy.TenantEntityListener;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 * 实体类基类，包含了实体ID、审计、租户 等公共信息
 */
@FilterDef(name= TenantConsts.Tenant_Filter_Name, parameters={@ParamDef(name=TenantConsts.Tenant_Filter_Param_Name, type="string")})
@Filters({@Filter(name=TenantConsts.Tenant_Filter_Name, condition="tenant_id = :"+TenantConsts.Tenant_Filter_Param_Name)})
@EntityListeners(TenantEntityListener.class)
@MappedSuperclass
public class EntityBase<T extends Serializable> implements Serializable, Entity<T> {
    protected static final int Default_Max_VChar_Length = 4000;
    protected static final int Default_Name_Length = 255;
    protected static final int Default_Description_Length = 255;
    protected static final int Default_Id_Length = 36;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.central.jpa.domain.entity.GenerateSnowflakeId")
    //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    protected T id;

//    @Id
//    //@GeneratedValue(strategy=GenerationType.IDENTITY) // MYSQL时可以这样使用自增
//    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
//    @GeneratedValue(generator="idGenerator")
//    protected T id;

//    @Embedded
//    private AuditInfo auditInfo;

//    @Embedded
//    private MayHasTenant tenant;

    @Column(name = "tenant_id")
    protected String tenantId;

    @Override
    public T getId() {
        return id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
