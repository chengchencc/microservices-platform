package com.central.jpa.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
//    @Override
//    @Query("select t from #{#entityName} t where t.id = :id")
//    Optional<T> findById(@Param("id") ID id);
}
