package com.central.jpa.domain.entity;

import com.central.jpa.ISession;
import com.central.jpa.utils.Clock;
import com.central.jpa.utils.SpringContextHolder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;

/**
 * Description com.inspur.edp.epp.infrastructure.domain.entities
 * Created by chengch
 * date on 2020/3/7
 */
@MappedSuperclass
@Getter
@Setter
public class CreationAuditedEntity extends EntityBase<String> {

    protected Date creationTime;
    protected String creatorUserId;

    @PrePersist
    public void touchOnCreation(){
        ISession session = SpringContextHolder.getBean(ISession.class);
        this.creatorUserId = session.getUserId();
        this.creationTime = Clock.now();
    }

}
