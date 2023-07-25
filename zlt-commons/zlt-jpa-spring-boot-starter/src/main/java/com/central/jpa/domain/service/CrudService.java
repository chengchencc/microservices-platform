package com.central.jpa.domain.service;

import com.central.jpa.domain.entity.EntityBase;
import com.central.jpa.dto.PagedResultDto;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * 标准增删改查服务接口
 * Created by chengch
 * date on 2019/11/11
 */
public interface CrudService<TEntity extends EntityBase<TId>, TId extends Serializable> {

    /**
     * 查找所有
     * @return
     */
    List<TEntity> findAll();

    /**
     * 通过Id进行查询
     * @param id
     * @return
     */
    TEntity findById(TId id);

    /**
     * 分页取数
     * @param page 当前页数，从0开始
     * @param size 每页个数
     * @return
     */
    PagedResultDto<TEntity> findAll(int page, int size);

    /**
     * 分页取数，同时可支持排序
     * @param pageable
     * @return
     */
    PagedResultDto<TEntity> findAll(Pageable pageable);

    /**
     * 创建
     * @param dto
     * @return
     */
    TEntity create(TEntity dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    TEntity update(TEntity dto);

    /**
     * 删除，dto中只需要带着id即可
     * @param dto
     */
    void delete(TEntity dto);

    /**
     * 批量删除
     * @param ids
     */
    void deleteInBatch(List<TId> ids);

}
