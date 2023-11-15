package com.babasnack.demo.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.product.Service.ReviewPhotoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ReviewPhotoController {
	@Autowired
	private ReviewPhotoService reviewPhotoService;

	@PostMapping("/review/{rno}")
    public ResponseEntity<String> uploadReviewPhoto(@PathVariable Long rno,
            @RequestParam("photo") MultipartFile revPhoto) {
        if (!revPhoto.isEmpty()) {
            try {
                // 리뷰 사진 저장 로직
                reviewPhotoService.saveReviewPhoto(rno, revPhoto);

                return ResponseEntity.ok("리뷰사진 업로드 완료");
            } catch (Exception e) {
                log.error("Failed to save review photo: " + revPhoto.getOriginalFilename(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰사진 업로드 실패");
            }
        } else {
            return ResponseEntity.badRequest().body("사진이 비어있습니다.");
        }
    }

    @GetMapping("/review/{rno}")
    public ResponseEntity<List<ReviewPhoto>> getReviewPhotos(@PathVariable Long rno) {
        // 특정 리뷰에 연관된 사진들 조회 처리 로직
        List<ReviewPhoto> reviewPhotos = reviewPhotoService.findReviewPhotosByRno(rno);
        return ResponseEntity.ok(reviewPhotos);
    }
}
