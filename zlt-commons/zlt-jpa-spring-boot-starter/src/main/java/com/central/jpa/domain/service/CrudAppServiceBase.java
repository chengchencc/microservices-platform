package com.central.jpa.domain.service;


import com.central.jpa.domain.entity.EntityBase;
import com.central.jpa.dto.EntityDto;
import com.central.jpa.dto.PagedResultDto;
import com.central.jpa.utils.ModelMapperUtils;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description com.inspur.edp.epp.framework.core.domain
 * Created by chengch
 * date on 2019/11/7
 */
public abstract class CrudAppServiceBase<TRepository extends JpaRepository<TEntity,TKey>,
        TEntity extends EntityBase<TKey>,
        TDto extends EntityDto<TKey>,
        TKey extends Serializable>
        extends ApplicationService implements CrudAppService<TDto,TKey> {

    protected TRepository repository;

    public CrudAppServiceBase(TRepository repository) {
        this.repository = repository;
    }


    public List<TDto> findAll(){
        List<TEntity> entities = repository.findAll();
        return entities.stream().map(s->map(s)).collect(Collectors.toList());
    }

    /**
     * 分页取数
     * @param page 当前页号
     * @param size 每页显示的个数
     * @return 分页数据
     */
    public PagedResultDto<TDto> findAll(int page, int size){
        Page<TEntity> entities = repository.findAll(PageRequest.of(page,size));
        PagedResultDto<TDto> pagedDtos = map(entities);
        return pagedDtos;
    }

    /**
     * 分页取数
     * @param pageable
     * @return
     */
    public PagedResultDto<TDto> findAll(Pageable pageable){
        Page<TEntity> entities = repository.findAll(pageable);
        PagedResultDto<TDto> pagedDtos = map(entities);
        return pagedDtos;
    }

    @Transactional
    public TDto findById(TKey id){
        TEntity entity = repository.findById(id).orElse(null);
        return map(entity);
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    public TDto create(TDto dto){
        TEntity entity = map(dto);
        TEntity saveEntity = repository.save(entity);
        return map(saveEntity);
    }

    @Override
    public List<TDto> create(List<TDto> dtos) {
        List<TEntity> entities = dtos.stream().map(this::map).collect(Collectors.toList());
        repository.saveAll(entities);
        return entities.stream().map(this::map).collect(Collectors.toList());
    }

    /**
     * 更新
     * @param dto
     * @return
     */
    @Override
    public TDto update(TDto dto){
        TEntity entity = map(dto);
        repository.save(entity);
        return map(entity);
    }

    /**
     * 通过Id删除
     * @param id primaryKey
     */
    @Override
    public void deleteById(TKey id){
        repository.deleteById(id);
    }

    /**
     * 删除实体
     * @param dto
     */
    @Override
    public void delete(TDto dto){
        this.deleteById(dto.getId());
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteInBatch(List<TKey> ids){
        List<TEntity> entities = this.repository.findAllById(ids);
        this.repository.deleteInBatch(entities);
    }


//    protected Class<TEntity> getEntityType(){
//        Class<TEntity> tClass = (Class<TEntity>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//        return tClass;
//    }

    protected TEntity map(TDto dto){
        if (dto == null) return null;
        return ModelMapperUtils.map(dto,getEntityType());
    }

    protected TDto map(TEntity entity){
        if(entity == null) return null;
        return ModelMapperUtils.map(entity,getDtoType());
    }

    protected PagedResultDto<TDto> map(Page<TEntity> entities){
        Page<TDto> pagedDtos = entities.map(entity -> map(entity));
        PagedResultDto<TDto> pagedResultDto = new PagedResultDto<>();
        pagedResultDto.setTotal(pagedDtos.getTotalElements());
        pagedResultDto.setItems(pagedDtos.getContent());
        return pagedResultDto;
    }


    public Class<TEntity> getEntityType(){
        return (Class<TEntity>) ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(1).getRawClass();
    }

    public Class<TDto> getDtoType(){
        return (Class<TDto>) ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(2).getRawClass();
    }
}
