package com.central.jpa.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.domain.entities
 * Created by chengch
 * date on 2019/10/29
 */
@Embeddable
@Data
public class TreeEmbedding<T> {

    /**
     * 父节点
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "parentId",referencedColumnName = "id")
    protected T parent;

    /**
     * children
     */
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parentId")
    protected List<T> children = new ArrayList<>();
    /**
     * 分级路径
     */
    protected String path;
    /**
     * 级数
     */
    @Column(nullable = true)
    protected int tLevel;
    /**
     * 是否叶子结点
     */
    protected boolean isLeaf;
}
