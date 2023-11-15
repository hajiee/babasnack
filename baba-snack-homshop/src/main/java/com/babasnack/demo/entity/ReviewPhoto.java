package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPhoto {
	private Long rno;
    private Long reviewImgNo;
    private String reviewImg;
    private String reviewSaveImg;
    private String reviewImgUrl;

    public void updateReviewImg(String oriImgName, String imgName, String imgUrl) {
        this.reviewImg = oriImgName;
        this.reviewSaveImg = imgName;
        this.reviewImgUrl = imgUrl;
    }
}
