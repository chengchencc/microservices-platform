package com.central.system.dictionary;


import com.central.jpa.domain.entity.AuditEntityBase;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;


/**
 * 数据字典值表
 *
 * @author chengchen
 * @date 2021-01-07 13:46:17
 */
@Getter
@Setter
//@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor
@Table
@Entity
public class SysDictItem extends AuditEntityBase<String> {
//    @ApiModelProperty(value = "字典编码:外键")
    @NonNull
    @Column(nullable = false)
    private String dictId;
//    @ApiModelProperty(value = "字典值编码")
    @NonNull
    @Column(nullable = false)
    private String code;
//    @ApiModelProperty(value = "字典值")
    private String value;
//    @ApiModelProperty(value = "父字典值编码")
    private String parentId;
//    @ApiModelProperty(value = "排序")
    private Integer sortNo;
//    @ApiModelProperty(value = "扩展字段a")
    private String extA;
//    @ApiModelProperty(value = "扩展字段b")
    private String extB;
//    @ApiModelProperty(value = "启用")
    private Boolean enable;

    @ManyToOne
    @JoinColumn(name = "dictId",insertable = false,updatable = false)
    private SysDict dict;
}
