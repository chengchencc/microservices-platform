package com.central.system.tenancy;

import com.central.jpa.domain.service.BaseRepository;

public interface TenantInfoRepository extends BaseRepository<TenantInfo,String> {
    boolean existsByUrl(String url);
}
