package com.central.system.dictionary;

import com.central.jpa.dto.EntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link SysDictItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDictItemDto extends EntityDto<String> {
    String dictId;
    String code;
    String value;
    String parentId;
    Integer sortNo;
    String extA;
    String extB;
    Boolean enable;
}