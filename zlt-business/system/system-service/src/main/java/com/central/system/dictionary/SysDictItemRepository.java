package com.central.system.dictionary;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysDictItemRepository extends org.springframework.data.jpa.repository.JpaRepository<SysDictItem, String> {
    Optional<SysDictItem> findByDict_Id(Long id);

}
