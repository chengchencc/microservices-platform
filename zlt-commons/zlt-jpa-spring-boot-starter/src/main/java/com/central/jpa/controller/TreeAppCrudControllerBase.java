package com.central.jpa.controller;

import com.central.jpa.domain.service.TreeAppService;
import com.central.jpa.dto.TreeEntityDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TreeAppCrudControllerBase<TService extends TreeAppService<TEntityDto,Tkey>,TEntityDto extends TreeEntityDto<TEntityDto,Tkey>,Tkey>
        extends CrudControllerBase<TService,TEntityDto,Tkey> {

    public TreeAppCrudControllerBase(TService service) {
        super(service);
    }

    /**
     * 新增子级
     * @param dto
     * @param id
     * @return
     */
    @PostMapping("/child/{id}")
    public TEntityDto  addChild(@RequestBody TEntityDto dto, @PathVariable(name = "id")Tkey id){
        return service.addChild(dto,id);
    }


    /**
     * 更新
     * @param dto
     * @return
     */
    @PutMapping
    public TEntityDto update(@RequestBody TEntityDto dto) {
        return service.update(dto);
    }
}
