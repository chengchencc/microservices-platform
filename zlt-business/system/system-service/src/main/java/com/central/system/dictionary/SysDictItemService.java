package com.central.system.dictionary;


import com.central.jpa.domain.service.CrudAppService;
import com.central.jpa.tenancy.TenantContext;

@TenantContext
public interface SysDictItemService extends CrudAppService<SysDictItemDto,String> {

}
