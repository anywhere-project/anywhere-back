package com.project.anywhere.common.object;

import lombok.Getter;

@Getter
public class Attractions {
    private Integer attractionId;
    private Integer areaId;
    private String attractionName;
    private String attractionAddress;

    public Attractions(
        Integer attractionId, Integer areaId, String attractionName, String attractionAddress
        ) {
        this.attractionId = attractionId;
        this.areaId = areaId;
        this.attractionName = attractionName;
        this.attractionAddress = attractionAddress;
    }
}
