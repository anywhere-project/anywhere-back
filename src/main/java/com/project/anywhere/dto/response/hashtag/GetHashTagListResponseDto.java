package com.project.anywhere.dto.response.hashtag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.HashTag;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.repository.resultset.GetHashTagResultSet;

import lombok.Getter;

@Getter
public class GetHashTagListResponseDto extends ResponseDto {

    List<HashTag> hashTags;

    public GetHashTagListResponseDto(List<GetHashTagResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.hashTags = HashTag.getList(resultSets);
    }

    public static ResponseEntity<GetHashTagListResponseDto> success(List<GetHashTagResultSet> resultSets) {
        GetHashTagListResponseDto responseBody = new GetHashTagListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    

}
