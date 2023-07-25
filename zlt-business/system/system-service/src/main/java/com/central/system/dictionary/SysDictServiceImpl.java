package com.central.system.dictionary;

import com.central.jpa.domain.service.CrudAppServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends CrudAppServiceBase<SysDictRepository,SysDict,SysDictDto,String> implements SysDictService {
    private final SysDictItemRepository itemRepository;

    public SysDictServiceImpl(SysDictRepository repository, SysDictItemRepository itemRepository) {
        super(repository);
        this.itemRepository = itemRepository;
    }
    


}
