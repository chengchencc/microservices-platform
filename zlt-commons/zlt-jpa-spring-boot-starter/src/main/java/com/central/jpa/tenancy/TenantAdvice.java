package com.central.jpa.tenancy;

import com.central.jpa.ISession;
import com.central.jpa.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Method;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/21
 */
@Component
@Aspect
public class TenantAdvice {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ISession session;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.central.jpa.tenancy.TenantContext)")
    public void tenantContextPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint
     * @return
     */
    @Around("tenantContextPointcut()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TenantContext tenantContext = method.getAnnotation(TenantContext.class);

        Object proceed;

        if (tenantContext.useTenantFilter()) {

            String tenantId = tenantContext.tenantId();

            if (StringUtils.isNullOrEmpty(tenantContext.tenantId())) {
                tenantId = session.getTenantId();
            }
            //设置 tenant filter
            Session jpaSession = entityManager.unwrap(Session.class);
            Filter filter = jpaSession.enableFilter(TenantConsts.Tenant_Filter_Name);
            filter.setParameter(TenantConsts.Tenant_Filter_Param_Name, tenantId);
            filter.validate();

            proceed = joinPoint.proceed();

            jpaSession.disableFilter(TenantConsts.Tenant_Filter_Name);

        }else {
            proceed = joinPoint.proceed();
        }
        return proceed;
    }


}
