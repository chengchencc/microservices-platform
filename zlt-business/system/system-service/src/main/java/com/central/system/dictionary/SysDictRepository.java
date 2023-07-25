package com.central.system.dictionary;

import com.central.jpa.domain.service.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictRepository extends BaseRepository<SysDict,String> {
}
