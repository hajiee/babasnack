package com.babasnack.demo.product.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ReviewPhoto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {
	@Data
	public static class WritePR{
		private String reviewNotice;
		private Boolean star;
		private Long pno;
		private List<MultipartFile> reviewPhoto;
	}
	
	// 관리자리뷰
	@Data
	public static class AWritePR{
		private Long rno;
		private String reviewNotice;
		private Long pno;
		private String reviewWrite="관리자";
	}
	
	//리뷰관리
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AminPR{
		private Long pno;
		private Long rno;
		private String reviewWrite;
	}
	
	// 리뷰목록
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class ListPR{
			private Long rno;
			private LocalDateTime reviewDate;
			private String reviewNotice;
			private Boolean star;
			private String reviewWrite;
			private List<ReviewPhoto> reviewPhoto;
		}
	
	
}
