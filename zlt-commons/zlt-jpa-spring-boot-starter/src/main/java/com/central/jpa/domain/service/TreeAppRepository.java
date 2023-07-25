package com.central.jpa.domain.service;


import com.central.jpa.domain.entity.TreeEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface TreeAppRepository<T extends TreeEntityBase<T,ID>,ID extends Serializable> extends JpaRepository<T,ID> {
    List<T> findAllByParentId(ID parentId);
    void deleteByParentId(ID parentId);
    List<T> findAllByStoreId(ID storeId);
}
