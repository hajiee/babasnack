package com.babasnack.demo.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.product.Service.ReviewPhotoService;

@Controller
public class ReviewPhotoController {
	@Autowired
	private ReviewPhotoService reviewPhotoService;

	@PostMapping("/review/{rno}")
	public ResponseEntity<String> uploadReviewPhoto(@PathVariable Long rno,
			@RequestParam("photo") MultipartFile photo) {
		// 리뷰 사진 업로드 처리 로직
		// reviewPhotoService.saveReviewPhoto()를 호출하여 사진을 저장
		return ResponseEntity.ok("리뷰사진 업로드완료");
	}

	@GetMapping("/review/{rno}")
	public ResponseEntity<List<ReviewPhoto>> getReviewPhotos(@PathVariable Long rno) {
		// 특정 리뷰에 연관된 사진들 조회 처리 로직
		List<ReviewPhoto> reviewPhotos = reviewPhotoService.findReviewPhotosByRno(rno);
		return ResponseEntity.ok(reviewPhotos);
	}

}
