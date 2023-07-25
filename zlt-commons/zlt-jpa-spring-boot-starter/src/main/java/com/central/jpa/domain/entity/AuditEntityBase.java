package com.central.jpa.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 */

@Data
@MappedSuperclass
@EntityListeners(value = AuditEntityListener.class)
public class AuditEntityBase<T extends Serializable> extends EntityBase<T> {

    public static final String DELETE_TIME = "deletion_time";
    public static final String DELETE_USER_ID = "deletion_user_id";
    public static final String IS_DELETE = "is_delete";

    @Column
    protected Date creationTime;
    @Column(length = 36)
    protected String creationUserId;
    @Column
    protected Date lastModifyTime;
    @Column(length = 36)
    protected String lastModifyUserId;
    @Column(name = "deletion_time")
    protected Date deletionTime;
    @Column(name = "deletion_user_id",length = 36)
    protected String deletionUserId;
    @Column(name = "is_delete",nullable = false)
    private Boolean isDelete = false;
}

