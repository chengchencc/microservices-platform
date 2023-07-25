package com.central.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.dto
 * Created by chengch
 * date on 2019/11/11
 */
@Getter
@Setter
public class TreeEntityDto<TEntityDto extends EntityDto<TKey>,TKey> extends EntityDto<TKey> {
    //@JsonProperty("key")
    protected TKey id;
    public TKey getKey(){
        return this.id;
    }
    /**
     * 父节点Id
     */
    protected TKey parentId;
    /**
     * 子节点列表
     */
    @JsonProperty("children")
    protected List<TEntityDto> children = new ArrayList<>();
    /**
     * 分级路径
     */
    protected String path;
    /**
     * 级数
     */
    @JsonProperty("level")
    protected int tlevel;
    /**
     * 是否叶子结点
     */
    @JsonProperty("isLeaf")
    protected boolean isLeaf;

    /**
     * 排序号
     */
    protected Integer sortOrder;

}
