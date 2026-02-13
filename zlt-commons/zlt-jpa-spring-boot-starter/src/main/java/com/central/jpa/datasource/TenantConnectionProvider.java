package com.central.jpa.datasource;

import com.central.jpa.tenancy.TenantDetailService;
import com.central.jpa.tenancy.TenantDetails;
import com.central.jpa.utils.StringUtils;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT;
import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER;

@Component
public class TenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl implements HibernatePropertiesCustomizer {

    private static final Map<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<>();

    @Autowired
    private DataSource masterDataSource;

    @Autowired
    private TenantDetailService tenantDetailService;
    @Override
    protected DataSource selectAnyDataSource() {
        return masterDataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        if (!DATA_SOURCE_MAP.containsKey(tenantIdentifier)) {
            DataSource datasource = createDatasource(tenantIdentifier);
            if (datasource == null) {
                return masterDataSource;
            }
            DATA_SOURCE_MAP.put(tenantIdentifier, datasource);
        }
        return DATA_SOURCE_MAP.get(tenantIdentifier);
    }

    /**
     * Customize the specified JPA vendor properties.
     *
     * @param hibernateProperties the JPA vendor properties to customize
     */
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(MULTI_TENANT_CONNECTION_PROVIDER, this);
        hibernateProperties.put(MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    }

    public DataSource createDatasource(String tenantId){
        TenantDetails tenantDetails = tenantDetailService.get(tenantId);
        if (StringUtils.isNotEmpty(tenantDetails.getUrl())){
            // TODO: 多租户时应该放开
//            return DataSourceBuilder.create()
//                    .url(tenantDetails.getUrl())
//                    .username(tenantDetails.getName())
//                    .password(tenantDetails.getPassword())
//                    .driverClassName(tenantDetails.getDriverClassName())
//                    .build();
            return masterDataSource;
        }else {
            return masterDataSource;
        }
    }
}
