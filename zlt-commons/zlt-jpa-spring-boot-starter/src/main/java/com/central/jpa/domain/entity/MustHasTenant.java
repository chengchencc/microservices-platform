package com.central.jpa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */
@Embeddable
public class MustHasTenant {
    @Column(length = 36,nullable = false)
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
