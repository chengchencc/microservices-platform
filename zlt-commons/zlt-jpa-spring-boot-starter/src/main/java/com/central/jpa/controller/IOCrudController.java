package com.central.jpa.controller;

import java.util.List;

public interface IOCrudController<TInput, IOutput, TId> {
    /**
     * 查找所有
     * @return
     */
    List<IOutput> findAll();

    /**
     * 通过Id进行查询
     * @param id
     * @return
     */
    IOutput findById(TId id);

    /**
     * 创建
     * @param input
     * @return
     */
    IOutput create(TInput input);

    /**
     * 更新
     * @param input
     * @return
     */
    IOutput update(TInput input);

    /**
     * 删除，input中只需要带着id即可
     * @param input
     */
    void delete(TInput input);
}
