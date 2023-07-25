package com.central.jpa.tenancy;

import org.hibernate.Filter;
import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Description com.inspur.edp.epp.infrastructure.tenancy
 * Created by chengch
 * date on 2020/3/23
 */
public class TenantFilterSession {

    private EntityManager entityManager;
    private Session jpaSession;
    private Filter filter;

    public TenantFilterSession(EntityManager entityManager) {
        this.jpaSession = entityManager.unwrap(Session.class);
    }

    public void enableTenantFilter(String tenantId){
        this.filter = jpaSession.enableFilter(TenantConsts.Tenant_Filter_Name);
        filter.setParameter(TenantConsts.Tenant_Filter_Param_Name, tenantId);
        filter.validate();
    }

    public void disableTenantFilter(){
        if (this.filter != null)
            this.jpaSession.disableFilter(TenantConsts.Tenant_Filter_Name);

        this.filter = null;
    }


}
