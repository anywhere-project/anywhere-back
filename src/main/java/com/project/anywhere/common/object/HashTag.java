package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.repository.resultset.GetHashTagResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {

    private String tagName;
    private Integer usageCount;

    public HashTag(GetHashTagResultSet resultSet) {
        this.tagName = resultSet.getTagName();
        this.usageCount = resultSet.getUsageCount();
    }

    public static List<HashTag> getList(List<GetHashTagResultSet> resultSets) {
        List<HashTag> hashTags = new ArrayList<>();
        for (GetHashTagResultSet resultSet: resultSets) {
            HashTag hashTag = new HashTag(resultSet);
            hashTags.add(hashTag);
        }

        return hashTags;
    }
    
}
