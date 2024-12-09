package com.project.anywhere.entity.pk;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewScrapPk {
    
    @Column(name = "user_id")
    private String userId;
    @Column(name = "review_id")
    private Integer reviewId;

}
