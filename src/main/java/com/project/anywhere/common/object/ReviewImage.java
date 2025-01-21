package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.repository.resultset.GetImageResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImage {
    private Integer imageId;
    private Integer reviewId;
    private String imageUrl;
    private Integer imageOrder;

    public ReviewImage(GetImageResultSet resultSet) {
        this.imageId = resultSet.getImageId();
        this.reviewId = resultSet.getReviewId();
        this.imageUrl = resultSet.getImageUrl();
        this.imageOrder = resultSet.getImageOrder();
    }

    public static List<ReviewImage> getList(List<GetImageResultSet> resultSets) {
        List<ReviewImage> images = new ArrayList<>();
        for (GetImageResultSet resultSet: resultSets) {
            ReviewImage image = new ReviewImage(resultSet);
            images.add(image);
        }

        return images;
    }
}
