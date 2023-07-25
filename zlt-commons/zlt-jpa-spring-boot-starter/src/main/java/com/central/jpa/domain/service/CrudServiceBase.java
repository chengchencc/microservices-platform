package com.central.jpa.domain.service;


import com.central.jpa.domain.entity.EntityBase;
import com.central.jpa.dto.PagedResultDto;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.core.domain
 * Created by chengch
 * date on 2019/11/7
 */
public abstract class CrudServiceBase<TRepository extends JpaRepository<TEntity,TKey>,
        TEntity extends EntityBase<TKey>,
        TKey extends Serializable>
        extends ApplicationService {

    protected TRepository repository;

    public CrudServiceBase(TRepository repository) {
        this.repository = repository;
    }


    public List<TEntity> findAll(){
        List<TEntity> entities = repository.findAll();
        return entities;
    }

    /**
     * 分页取数
     * @param page 当前页号
     * @param size 每页显示的个数
     * @return 分页数据
     */
    public PagedResultDto<TEntity> findAll(int page, int size){
        Page<TEntity> entities = repository.findAll(PageRequest.of(page,size));
        PagedResultDto<TEntity> pagedDtos = map(entities);
        return pagedDtos;
    }

    /**
     * 分页取数
     * @param pageable
     * @return
     */
    public PagedResultDto<TEntity> findAll(Pageable pageable){
        Page<TEntity> entities = repository.findAll(pageable);
        PagedResultDto<TEntity> pagedDtos = map(entities);
        return pagedDtos;
    }

    public TEntity findById(TKey id){
        TEntity entity = repository.findById(id).orElse(null);
        return entity;
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    public TEntity create(TEntity dto){
        TEntity saveEntity = repository.save(dto);
        return saveEntity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    public TEntity update(TEntity entity){
        repository.save(entity);
        return entity;
    }

    /**
     * 通过Id删除
     * @param id primaryKey
     */
    public void deleteById(TKey id){
        repository.deleteById(id);
    }

    /**
     * 删除实体
     * @param dto
     */
    public void delete(TEntity dto){
        this.deleteById(dto.getId());
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteInBatch(List<TKey> ids){
        List<TEntity> entities = this.repository.findAllById(ids);
        this.repository.deleteInBatch(entities);
    }

    protected PagedResultDto<TEntity> map(Page<TEntity> entities){
        Page<TEntity> pagedDtos = entities;
        PagedResultDto<TEntity> pagedResultDto = new PagedResultDto<>();
        pagedResultDto.setTotal(entities.getTotalElements());
        pagedResultDto.setItems(entities.getContent());
        return pagedResultDto;
    }


    public Class<TEntity> getEntityType(){
        return (Class<TEntity>) ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(1).getRawClass();
    }
}
