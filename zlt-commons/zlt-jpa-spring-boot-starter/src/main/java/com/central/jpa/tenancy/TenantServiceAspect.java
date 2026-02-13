package com.central.jpa.tenancy;

import com.central.common.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class TenantServiceAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    void isRepository() {
        /* aspect */
    }

    @Pointcut(value = "isRepository()")
    void enableMultiTenancy() {
        /* aspect */
    }

    @Around("execution(public * *(..)) && enableMultiTenancy()")
    public Object aroundExecution(final ProceedingJoinPoint pjp) throws Throwable {
        if (AopUtils.getTargetClass(pjp.getTarget()).isAssignableFrom(MultiTenancyRepository.class)) {
            String tenantId = Optional.ofNullable(TenantContextHolder.getTenant()).orElse(TenantContextHolder.DEFAULT_TENANT_ID);
            final Filter filter = this.entityManager.unwrap(Session.class) // requires transaction
                    .enableFilter(TenantConsts.Tenant_Filter_Name)
                    .setParameter(TenantConsts.Tenant_Filter_Param_Name, tenantId);
            filter.validate();
        }

        return pjp.proceed();
    }
}

//@AfterReturning(pointcut = "execution(* javax.persistence.EntityManagerFactory+.createEntityManager(..))", returning = "entityManager")
//https://github.com/spring-projects/spring-framework/issues/25125