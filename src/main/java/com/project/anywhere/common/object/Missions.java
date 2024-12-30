package com.project.anywhere.common.object;

import lombok.Getter;

@Getter
public class Missions {
    private Integer missionId;
    private String missionName;
    private String missionContent;

    public Missions (Integer missionId, String missionName,String missionContent ) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionContent = missionContent;
    };
}
