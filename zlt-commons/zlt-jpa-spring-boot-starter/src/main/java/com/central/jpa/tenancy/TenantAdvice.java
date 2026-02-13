package com.central.jpa.tenancy;

import com.central.jpa.ISession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/21
 */
//@Component
//@Aspect
public class TenantAdvice {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ISession session;

    /**
     * 配置切入点
     */
//    @Pointcut("@annotation(com.central.jpa.tenancy.TenantContext)")
//    public void tenantContextPointcut() {
//        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
//    }

//    @Pointcut("@within(com.central.jpa.tenancy.TenantContext)")
//    public void tenantContextClassPointcut(){
//
//    }
    // TODO: 这种方式只针对服务类、服务类方法，包括集成类调用父类方法都会有效，但是把注解放到repository接口上不会有效
    @Around("execution(public * com.central..*.* (..)) && ( @target(tenantContext) || @annotation(tenantContext))")
    public Object AroundClass(ProceedingJoinPoint joinPoint,TenantContext tenantContext) throws Throwable {
        System.out.printf("aaa");
        return joinPoint.proceed();
    }

        /**
         * 配置环绕通知,使用在方法logPointcut()上注册的切入点
         *
         * @param joinPoint
         * @return
         */
//    @Around("tenantContextPointcut()")
//    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        TenantContext tenantContext = method.getAnnotation(TenantContext.class);
//
//        if (tenantContext == null){
//
//        }
//
//        Object proceed;
//
//        if (tenantContext.useTenantFilter()) {
//
//            String tenantId = tenantContext.tenantId();
//
//            if (StringUtils.isNullOrEmpty(tenantContext.tenantId())) {
//                tenantId = session.getTenantId();
//            }
//            //设置 tenant filter
//            Session jpaSession = entityManager.unwrap(Session.class);
//            Filter filter = jpaSession.enableFilter(TenantConsts.Tenant_Filter_Name);
//            filter.setParameter(TenantConsts.Tenant_Filter_Param_Name, tenantId);
//            filter.validate();
//
//            proceed = joinPoint.proceed();
//
//            jpaSession.disableFilter(TenantConsts.Tenant_Filter_Name);
//
//        }else {
//            proceed = joinPoint.proceed();
//        }
//        return proceed;
//    }


}
