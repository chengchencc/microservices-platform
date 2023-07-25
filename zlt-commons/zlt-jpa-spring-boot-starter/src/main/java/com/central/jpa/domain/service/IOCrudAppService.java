package com.central.jpa.domain.service;


import com.central.jpa.dto.EntityDto;
import com.central.jpa.dto.PagedResultDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOCrudAppService<TInput extends EntityDto<TId>, TOutput extends EntityDto<TId>, TId> {
    /**
     * 查找所有
     *
     * @return
     */
    List<TOutput> findAll();

    /**
     * 分页取数
     *
     * @param page 当前页数，从0开始
     * @param size 每页个数
     * @return
     */
    PagedResultDto<TOutput> findAll(int page, int size);

    /**
     * 分页取数，同时可支持排序
     *
     * @param pageable
     * @return
     */
    PagedResultDto<TOutput> findAll(Pageable pageable);

    /**
     * 通过Id进行查询
     *
     * @param id
     * @return
     */
    TOutput findById(TId id);

    /**
     * 创建
     *
     * @param input
     * @return
     */
    TOutput create(TInput input);

    /**
     * 更新
     *
     * @param input
     * @return
     */
    TOutput update(TInput input);

    /**
     * 删除
     *
     * @param input
     */
    void delete(TInput input);

    /**
     * 删除
     *
     * @param id
     */
    void delete(TId id);
}
