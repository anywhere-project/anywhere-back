package com.project.anywhere.entity;

import com.project.anywhere.dto.request.reviewImages.PostReviewImagesRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="review_images")
@Table(name = "review_images")
public class ReviewImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private Integer reviewId;
    private String imageUrl;
    private Integer imageOrder=0;

    public ReviewImagesEntity(PostReviewImagesRequestDto dto){
        this.imageUrl = dto.getImageUrl();
    }


    
}
