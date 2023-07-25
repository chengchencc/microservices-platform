package com.central.jpa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */
@Embeddable
public class SoftDelete {
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
