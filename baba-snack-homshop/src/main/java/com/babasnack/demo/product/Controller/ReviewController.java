package com.babasnack.demo.product.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.product.Service.ReviewService;
import com.babasnack.demo.product.dto.ReviewDto;

@Controller
@RequestMapping("/product")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	 @GetMapping("/product-read/{pno}")
	    public String getProductReadPage(@PathVariable Long pno, Model model) {
	        // 리뷰가 추가된 `product-read` 페이지에 리뷰 목록을 포함하여 반환
	        List<Review> reviews = reviewService.getReviewsByProduct(pno);
	        model.addAttribute("reviews", reviews);
	        return "product/product-read";
	    }

	 @PostMapping("/product-read/{pno}/add-review")
	 public String saveReview(@PathVariable("pno") Long pno, @ModelAttribute("review") Review review, Principal principal,
	         @RequestParam("reviewPhoto") List<MultipartFile> revphoto, Model model) {
		// 현재 로그인한 사용자 정보 가져오기
		    String username = principal.getName();

		    // 상품 구매자인 경우에만 리뷰 작성 가능
		    if (review.getReviewWrite().equals(username)) {
		        // ReviewDto.WritePR 객체 생성 및 값 설정
		        ReviewDto.WritePR reviewDto = new ReviewDto.WritePR();
		        reviewDto.setReviewNotice(review.getReviewNotice());
		        reviewDto.setStar(review.getStar());
		        reviewDto.setPno(review.getPno());
		        reviewDto.setReviewWrite(review.getReviewWrite());

		        // 구매자의 리뷰 및 사진 저장 로직 추가
		        try {
		            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		            reviewService.saveReview(reviewDto, revphoto, authentication);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } else {
		        throw new IllegalArgumentException("Only buyers can write reviews.");
		    }

		    // 리뷰가 추가된 `product-read` 페이지에 리뷰 목록을 포함하여 반환
		    List<Review> reviews = reviewService.getReviewsByProduct(pno);
		    model.addAttribute("reviews", reviews);
	     return "redirect:/product/product-read/" + pno;
	 }

	    @Secured("ROLE_ADMIN") // 관리자에 대해서만 접근 허용
	    @GetMapping("/reviews-user")
	    public boolean isAdminUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        return authentication.getAuthorities().stream()
	                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	    }
}
