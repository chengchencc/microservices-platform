package com.central.system.dictionary;

import com.central.jpa.domain.entity.AuditEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 数据字典表
 *
 * @author chengchen
 * @date 2020-10-16 11:38:28
 */
@Getter
@Setter
@Table
@Entity
public class SysDict extends AuditEntityBase<String> {
    private static final long serialVersionUID=1L;
    /**
     * 字典编号
     */
    private String code;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 父字典编码
     */
    private String parentCode;

    @OneToMany(mappedBy = "dict",fetch = FetchType.LAZY)
    private Set<SysDictItem> items;
}

