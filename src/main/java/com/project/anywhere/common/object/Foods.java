package com.project.anywhere.common.object;

import lombok.Getter;

@Getter
public class Foods {
    private Integer foodId;
    private String foodName;

    public Foods (Integer foodId, String foodName) {
        this.foodId = foodId;
        this.foodName = foodName;
    };
}
