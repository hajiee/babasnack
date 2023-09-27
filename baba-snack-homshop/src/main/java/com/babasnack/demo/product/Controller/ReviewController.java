package com.babasnack.demo.product.Controller;

import java.io.IOException;
import java.security.Principal;

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
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.product.Service.ReviewService;

@RequestMapping("/review")
@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Secured("ROLE_BUYER") // 상품 구매자 역할에 대해서만 접근 허용
    @PostMapping("/reviews")
    public String saveReview(@ModelAttribute("review") Review review, Principal principal, MultipartFile photo) {
        // 현재 로그인한 사용자 정보 가져오기
        String username = principal.getName();

        // 상품 구매자인 경우에만 리뷰 작성 가능
        if (review.getReviewWrite().equals(username)) {
			// 구매자의 리뷰 및 사진 저장 로직 추가
            try {
				reviewService.saveReviewWithPhoto(review, photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
            return "redirect:/product/" + review.getPno();
        } else if (isAdminUser()) {
            // 관리자인 경우에만 리뷰 달기 가능
            // 관리자의 리뷰 저장 로직 추가
            review.setReviewWrite(username);
            reviewService.saveReview(review);
            return "redirect:/product/" + review.getPno();
        } else {
            throw new IllegalArgumentException("Only buyers or administrators can write reviews.");
        }
    }

	@Secured("ROLE_ADMIN") // 관리자에 대해서만 접근 허용
    @PostMapping("/reviews-user")
    private boolean isAdminUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
         return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
     }
    
    @GetMapping("/product/{pno}")
    public void getProductPage(@PathVariable Long pno, Model model, Principal principal) {
        // 현재 로그인한 사용자 정보 가져오기
        String username = principal.getName();

        // 사용자가 상품 구매자인지 확인
        boolean hasRoleBuyer = reviewService.isBuyer(username, pno);

        // 사용자가 관리자인지 확인
        boolean hasRoleAdmin = reviewService.isAdminUser();

        model.addAttribute("hasRoleBuyer", hasRoleBuyer);
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

   }
}
