package com.central.jpa.controller;



import com.central.jpa.domain.service.IOCrudAppService;
import com.central.jpa.dto.EntityDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class IOCrudControllerBase<TService extends IOCrudAppService<TInput , TOutput, TId>, TInput extends EntityDto<TId>, TOutput extends EntityDto<TId>, TId>
        extends ControllerBase
        implements IOCrudController<TInput, TOutput, TId> {
    protected TService service;

    public IOCrudControllerBase(TService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public List<TOutput> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public TOutput findById(@PathVariable("id") TId id) {
        return this.service.findById(id);
    }

    @PostMapping
    @Override
    public TOutput create(@RequestBody TInput input) {
        return this.service.create(input);
    }

    @PutMapping
    @Override
    public TOutput update(@RequestBody TInput input) {
        return this.service.update(input);
    }

    @DeleteMapping
    @Override
    public void delete(TInput input) {
        this.service.delete(input);
    }
}
