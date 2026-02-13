package com.central.jpa.tenancy;


import com.central.common.context.TenantContextHolder;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

public class MultiTenancyRepositoryFactory<T, I> extends JpaRepositoryFactory {

  private EntityManager em;

  /**
   * Creates a new {@link JpaRepositoryFactory}.
   *
   * @param entityManager must not be {@literal null}
   */
  public MultiTenancyRepositoryFactory(EntityManager entityManager) {
    super(entityManager);
    this.em = entityManager;
  }

  @Override
  protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
    if (TenantSupport.class.isAssignableFrom(metadata.getDomainType())){
      return MultiTenancyRepository.class;
    }

    return super.getRepositoryBaseClass(metadata);
  }

  private void enableFilter() {
    Session session = em.unwrap(Session.class);
    Filter filter = session.enableFilter(TenantConsts.Tenant_Filter_Name);
    filter.setParameter(TenantConsts.Tenant_Filter_Param_Name, TenantContextHolder.getTenantOrDefault());
  }
}
