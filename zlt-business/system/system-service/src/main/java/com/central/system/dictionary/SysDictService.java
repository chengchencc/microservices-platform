package com.central.system.dictionary;


import com.central.jpa.domain.service.CrudAppService;
import com.central.jpa.tenancy.TenantContext;

import java.util.Optional;

@TenantContext
public interface SysDictService extends CrudAppService<SysDictDto,String> {

    Optional<SysDict> findByIdTest(String id);

    String get();
}
