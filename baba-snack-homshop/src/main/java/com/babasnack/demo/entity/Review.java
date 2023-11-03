package com.babasnack.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
	private Long rno;
	private LocalDateTime reviewDate = LocalDateTime.now();
	private String reviewNotice;
	private Boolean star;
	private Long pno;
	private String reviewWrite;
	
	// Review 엔티티에 사진 목록을 저장할 컬렉션 필드 추가
	// 해당 상품과 연관된 사진들을 저장하기 위한 컬렉션
	private List<ReviewPhoto> reviewPhotos;
    public List<ReviewPhoto> getPhotos() {
        return reviewPhotos;
    }
    public void setPhotos(List<ReviewPhoto> photoData) {
        this.reviewPhotos = photoData;
    }
}
