package com.central.jpa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.dto
 * Created by chengch
 * date on 2019/11/4
 */
@Data
public class PagedResultDto<T> {

    private long total;

    private List<T> items = new ArrayList<>();

    public PagedResultDto() {
    }

    public PagedResultDto(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
