package com.central.system.dictionary;

import com.central.jpa.dto.EntityDto;
import lombok.Data;

import java.util.Set;

@Data
public class SysDictDto extends EntityDto<String> {
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

    private Set<SysDictItemDto> items;
}
