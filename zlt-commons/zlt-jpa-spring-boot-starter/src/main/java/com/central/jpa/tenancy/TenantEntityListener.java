package com.central.jpa.tenancy;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Description com.inspur.edp.epp.infrastructure.aop.tenancy
 * Created by chengch
 * date on 2020/3/23
 */
//@Component
public class TenantEntityListener {

//    @Autowired
//    ISession session;

    public TenantEntityListener() {
//        this.session = SpringContextHolder.getBean(ISession.class);
    }

    @PrePersist
    public void touchForCreate(Object entity) {
        if (entity instanceof TenantSupport) {
//            TenantContextHolder.getTenantId();
            ((TenantSupport) entity).setTenantId("null");
//            ((TenantSupport) entity).setTenantId(session.getTenantId());
        }
    }

    @PreUpdate
    public void touchForUpdate(Object entity) {
        if (entity instanceof TenantSupport) {
            ((TenantSupport) entity).setTenantId("null");
//            ((TenantSupport) entity).setTenantId(session.getTenantId());
        }
    }


}
