package com.central.jpa.domain.service;

import com.central.jpa.ISession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description com.inspur.edp.epp.framework.core.common
 * Created by chengch
 * date on 2019/11/14
 */
public abstract class ApplicationService extends ServiceBase {
    @Autowired
    protected ISession session;

}
