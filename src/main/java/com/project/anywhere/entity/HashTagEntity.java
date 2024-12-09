package com.project.anywhere.entity;

import com.project.anywhere.dto.request.hashtag.PostHashTagRequestDto;

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
@Table(name = "hash_tag")
@Entity(name = "hash_tag")
public class HashTagEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;
    private String tagName;

    public HashTagEntity(PostHashTagRequestDto dto) {
        this.tagName = dto.getTagName();
    }

}