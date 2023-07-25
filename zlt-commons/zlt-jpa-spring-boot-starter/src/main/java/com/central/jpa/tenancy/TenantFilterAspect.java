package com.central.jpa.tenancy;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;

@Aspect
public class TenantFilterAspect {
    @Pointcut("execution (* org.hibernate.internal.SessionFactoryImpl.SessionBuilderImpl.openSession(..))")
    public void openSession() {
    }

    @AfterReturning(pointcut = "openSession()", returning = "session")
    public void afterOpenSession(Object session) {
        if (session != null && Session.class.isInstance(session)) {
//            final String tenantId = TenantContext.getTenantId();
            final String tenantId = "ttt";
            if (tenantId != null) {
                org.hibernate.Filter filter = ((Session) session).enableFilter("tenantFilter");
                filter.setParameter("tenantId", tenantId);
            }
        }
    }

}
