package com.central.jpa.tenancy;

import org.springframework.context.annotation.Configuration;

/**
 * 自定义EntityManager获取，并开启 tenantFilter
 */
@Configuration
public class MultiTenancyDatasourceConfig {

//    @Bean
//    @ConditionalOnMissingBean
//    public PlatformTransactionManager transactionManager(ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager() {
//            @Override
//            protected EntityManager createEntityManagerForTransaction() {
//                final EntityManager entityManager = super.createEntityManagerForTransaction();
//
//                Session session = entityManager.unwrap(Session.class);
//                try {
//                    Filter filter = session.enableFilter(TenantConsts.Tenant_Filter_Name);
//                    filter.setParameter(TenantConsts.Tenant_Filter_Param_Name, TenantContextHolder.getTenantOrDefault());
//                }catch (Exception e){
//                    logger.error(e.getMessage(),e);
//                }
//                return entityManager;
//            }
//        };
//        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
//
//        return transactionManager;
//    }

}
