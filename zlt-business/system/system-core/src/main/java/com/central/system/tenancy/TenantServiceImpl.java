package com.central.system.tenancy;

import com.central.jpa.domain.service.CrudAppServiceBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantServiceImpl extends CrudAppServiceBase<TenantInfoRepository,TenantInfo,TenantInfoDto,String> implements TenantService {


    public TenantServiceImpl(TenantInfoRepository repository) {
        super(repository);
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public TenantInfoDto create(TenantInfoDto dto) {
        TenantInfoDto tenantInfoDto = super.create(dto);

        boolean existsByUrl = repository.existsByUrl(dto.getUrl());
        if (!existsByUrl){
            //TODO:判断是否新的数据库链接地址的话，需要使用liquibase自动生成数据库表
            // build datasource
            // liquibase.update
        }

        return tenantInfoDto;
    }
}
