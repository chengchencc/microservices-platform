package com.central.jpa.datasource;

import com.central.common.context.TenantContextHolder;
import com.central.jpa.utils.StringUtils;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {
    /**
     * Resolve the current tenant identifier.
     *
     * @return The current tenant identifier
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = Optional.ofNullable(TenantContextHolder.getTenant()).orElse(TenantContextHolder.DEFAULT_TENANT_ID);
        return tenantId;
    }

    /**
     * Should we validate that the tenant identifier on "current sessions" that already exist when
     * {@link CurrentSessionContext#currentSession()} is called matches the value returned here from
     * {@link #resolveCurrentTenantIdentifier()}?
     *
     * @return {@code true} indicates that the extra validation will be performed; {@code false} indicates it will not.
     * @see TenantIdentifierMismatchException
     */
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    /**
     * Customize the specified JPA vendor properties.
     *
     * @param hibernateProperties the JPA vendor properties to customize
     */
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,this);
    }
}
