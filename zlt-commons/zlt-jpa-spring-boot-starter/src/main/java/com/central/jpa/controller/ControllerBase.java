package com.central.jpa.controller;

import com.central.jpa.ISession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description com.inspur.edp.epp.framework.web.common.entities
 * Created by chengch
 * date on 2019/11/15
 */
public abstract class ControllerBase {
    @Autowired(required = false)
    protected ISession session;
}
