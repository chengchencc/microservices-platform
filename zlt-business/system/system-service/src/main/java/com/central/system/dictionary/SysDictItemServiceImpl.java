package com.central.system.dictionary;

import com.central.jpa.domain.service.CrudAppServiceBase;
import org.springframework.stereotype.Service;

@Service
public class SysDictItemServiceImpl extends CrudAppServiceBase<SysDictItemRepository,SysDictItem,SysDictItemDto,String> implements SysDictItemService {
    public SysDictItemServiceImpl(SysDictItemRepository repository) {
        super(repository);
    }



}
