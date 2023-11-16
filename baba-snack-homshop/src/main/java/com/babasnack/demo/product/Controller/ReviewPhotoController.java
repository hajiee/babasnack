package com.babasnack.demo.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.product.Service.ReviewPhotoService;

import lombok.extern.log4j.Log4j2;

@RequestMapping
@Log4j2
@Controller
public class ReviewPhotoController {
	@Autowired
	private ReviewPhotoService reviewPhotoService;

	@PostMapping("/review/{rno}/revPhotos")
	public String uploadReviewPhoto(@PathVariable Long rno,
	        @RequestParam("revPhotos") MultipartFile revPhoto, Model model) {
	    if (!revPhoto.isEmpty()) {
	        try {
	            // 리뷰 사진 저장 로직
	            reviewPhotoService.saveReviewPhoto(rno, revPhoto);
	            model.addAttribute("message", "파일 " + revPhoto.getOriginalFilename() + "을(를) 저장했습니다.");
	        } catch (Exception e) {
	            log.error("Failed to save review photo: " + revPhoto.getOriginalFilename(), e);
	            model.addAttribute("error", "파일 " + revPhoto.getOriginalFilename() + "을(를) 저장하는 도중 오류가 발생했습니다.");
	        }
	    }

	    // 내용이 비어있는 경우 에러 처리 및 페이지 반환
	    if (model.getAttribute("message") == null && model.getAttribute("error") == null) {
	        model.addAttribute("error", "파일이 비어있습니다.");
	        return "error-page"; // 에러 페이지로 이동
	    }

	    return "result"; // 결과 페이지로 이동
	}

    @GetMapping("/review/{rno}")
    public ResponseEntity<List<ReviewPhoto>> getReviewPhotos(@PathVariable Long rno) {
        // 특정 리뷰에 연관된 사진들 조회 처리 로직
        List<ReviewPhoto> reviewPhotos = reviewPhotoService.findReviewPhotosByRno(rno);
        return ResponseEntity.ok(reviewPhotos);
    }
}
