package com.central.system.dictionary;

import com.central.jpa.domain.service.CrudAppServiceBase;
import com.central.jpa.tenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@TenantContext
public class SysDictServiceImpl extends CrudAppServiceBase<SysDictRepository,SysDict,SysDictDto,String> implements SysDictService {
    private final SysDictItemRepository itemRepository;

    public SysDictServiceImpl(SysDictRepository repository, SysDictItemRepository itemRepository) {
        super(repository);
        this.itemRepository = itemRepository;
    }

    @TenantContext
    public Optional<SysDict> findByIdTest(String id){
        return repository.findById(id);
    }

    @Override
    public String get() {
        return null;
    }

}
