package com.central.jpa.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.domain.entities
 * Created by chengch
 * date on 2019/11/7
 */
@Setter
@Getter
@MappedSuperclass
public class TreeEntityBase<T,TKey extends Serializable> extends EntityBase<TKey> {

    /**
     * 父节点ID
     */
    @Column(name = "parentId")
    protected TKey parentId;

    /**
     * 父节点
     */
//    @ManyToOne(optional = true,fetch = FetchType.LAZY)
//    @JoinColumn(name = "parentId",referencedColumnName = "id",insertable = false,updatable = false)

    @Transient
    protected T parent;

    /**
     * children
     */
//    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
//    @JoinColumn(name = "parentId",referencedColumnName = "id")
    @Transient
    protected List<T> children = new ArrayList<>();
    /**
     * 分级路径
     */
    protected String path;
    /**
     * 级数
     */
    @Column(nullable = true)
    protected int tlevel;
    /**
     * 是否叶子结点
     */
    protected boolean isLeaf;

    /**
     * 排序顺序
     */
    protected Integer sortOrder;

    /**库ID*/
    @Column(name = "storeid")
    protected TKey storeId;
}
