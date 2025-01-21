package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.MyRandomEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyRandom {
    
    private Integer randomId;
    private String userId;
    private String areaName;
    private String foodName;
    private String attractionName;
    private String missionName;

    public MyRandom(MyRandomEntity myRandomEntity) {
        this.randomId = myRandomEntity.getRandomId();
        this.userId = myRandomEntity.getUserId();
        this.areaName = myRandomEntity.getAreaName();
        this.foodName = myRandomEntity.getAreaName();
        this.attractionName = myRandomEntity.getAttractionName();
        this.missionName = myRandomEntity.getMissionName();
    }

    public static List<MyRandom> getList(List<MyRandomEntity> myRandomEntities) {
        List<MyRandom> myRandoms = new ArrayList<>();

        for (MyRandomEntity myRandomEntity : myRandomEntities) {
            MyRandom myRandom = new MyRandom(myRandomEntity);
            myRandoms.add(myRandom);
        }

        return myRandoms;
    }

}
