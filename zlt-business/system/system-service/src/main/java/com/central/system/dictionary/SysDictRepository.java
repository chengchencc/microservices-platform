package com.central.system.dictionary;

import com.central.jpa.domain.service.BaseRepository;
import com.central.jpa.tenancy.TenantContext;
import org.springframework.stereotype.Repository;

@Repository
@TenantContext
public interface SysDictRepository extends BaseRepository<SysDict,String> {
}
