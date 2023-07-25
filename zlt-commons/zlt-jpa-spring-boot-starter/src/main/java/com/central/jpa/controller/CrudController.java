package com.central.jpa.controller;

import com.central.jpa.dto.PagedRequestDto;
import com.central.jpa.dto.PagedResultDto;

import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.web.controllers
 * Created by chengch
 * date on 2019/11/14
 */
public interface CrudController<TDto,TId> {
    /**
     * 查找所有
     * @return
     */
    List<TDto> findAll();
    /**
     * 分页查找
     * @param dto
     * @return
     */
    public PagedResultDto<TDto> findAll(PagedRequestDto dto);
    /**
     * 通过Id进行查询
     * @param id
     * @return
     */
    TDto findById(TId id);

    /**
     * 创建
     * @param dto
     * @return
     */
    TDto create(TDto dto) throws Exception;

    /**
     * 更新
     * @param dto
     * @return
     */
    TDto update(TDto dto);

    /**
     * 删除，dto中只需要带着id即可
     * @param dto
     */
    void delete(TDto dto);

    void deleteById(TId id);
}
