package com.central.jpa.tenancy;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.EntityListeners;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/23
 */
//@FilterDef(name = TenantConsts.Tenant_Filter_Name,parameters = @ParamDef(name=TenantConsts.Tenant_Filter_Param_Name,type="string"))
//@Filter(name=TenantConsts.Tenant_Filter_Name,condition = "tenant_id = :tenantId")



//@FilterDef(name="filterByEmail", parameters={@ParamDef(name="email", type="string")})
//@Filters( {
//        @Filter(name="filterByEmail", condition=":email = email")
//} )
//TODO:这样做无效 https://stackoverflow.com/questions/22126851/how-to-extend-java-annotation
@Target({TYPE, PACKAGE})
@Retention(RUNTIME)
@FilterDef(name=TenantConsts.Tenant_Filter_Name, parameters={@ParamDef(name=TenantConsts.Tenant_Filter_Param_Name, type="string")})
@Filters({@Filter(name=TenantConsts.Tenant_Filter_Name, condition="tenant_id = :"+TenantConsts.Tenant_Filter_Param_Name)})
@EntityListeners(TenantEntityListener.class)
public @interface TenantFilter {
}
