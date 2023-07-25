package com.central.jpa.dto;

import lombok.Data;

import java.util.Date;

/**
 * Description com.inspur.edp.epp.infrastructure.dto
 * Created by chengch
 * date on 2020/3/5
 */
@Data
public class AuditEntityDto<TId> extends EntityDto<TId> {
    protected Date creationTime;
    protected String creationUserId;
    protected Date lastModifyTime;
    protected String lastModifyUserId;
    protected Date deletionTime;
    protected String deletionUserId;

}
