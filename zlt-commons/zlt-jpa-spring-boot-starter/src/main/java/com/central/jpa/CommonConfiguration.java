package com.central.jpa;

import com.central.jpa.tenancy.DefaultTenantDetailService;
import com.central.jpa.tenancy.TenantDetailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CommonConfiguration {

    @Bean
    @ConditionalOnMissingBean(TenantDetailService.class)
    public TenantDetailService tenantDetailService(DataSourceProperties dataSourceProperties){
        return new DefaultTenantDetailService(dataSourceProperties);
    }
}
