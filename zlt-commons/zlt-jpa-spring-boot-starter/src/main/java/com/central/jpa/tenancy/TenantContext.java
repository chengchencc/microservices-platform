package com.central.jpa.tenancy;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/21
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Transactional
public @interface TenantContext {
    String tenantId() default "";
    boolean useTenantFilter() default true;
}
