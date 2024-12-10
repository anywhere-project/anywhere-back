package com.project.anywhere.common.object;

import lombok.Getter;

@Getter
public class Areas {
    private Integer areaId;
    private String areaName;

    public Areas(Integer areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }
}
