package com.central.system.dictionary;

import com.central.jpa.domain.service.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysDictItemRepository extends BaseRepository<SysDictItem, String> {
    List<SysDictItem> findByDict_Id(String id);

    List<SysDictItem> findByDictId(String dictId);

}
