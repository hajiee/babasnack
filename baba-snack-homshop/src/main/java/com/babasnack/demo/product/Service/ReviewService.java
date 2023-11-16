package com.babasnack.demo.product.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.orderdetail.dao.OrderDetailDao;
import com.babasnack.demo.product.dao.ReviewDao;
import com.babasnack.demo.product.dto.ReviewDto;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
    private ReviewPhotoService reviewPhotoService;
	
	public void addProductReview(ReviewDto.WritePR dto, List<MultipartFile> reviewUploadPhoto, Authentication authentication) throws IOException {
        if (isBuyer(authentication.getName(), dto.getPno())) {
            String reviewWrite = authentication != null ? authentication.getName() : null;

            // ReviewDto에서 필요한 정보를 가져와서 Review 엔티티 생성
            Review review = Review.builder()
                    .reviewDate(LocalDate.now())
                    .reviewNotice(dto.getReviewNotice())
                    .star(dto.getStar())
                    .pno(dto.getPno())
                    .reviewWrite(reviewWrite)
                    .build();

            // Review 엔티티 저장
            reviewDao.addProductReview(review);

            // 리뷰 사진 저장 로직
            for (MultipartFile revPhoto : reviewUploadPhoto) {
                if (revPhoto != null && !revPhoto.isEmpty()) { // photo가 null이 아니고 비어있지 않은 경우에만 처리
                    reviewPhotoService.saveReviewPhoto(review.getRno(), revPhoto);
                }
            }
        } else {
            throw new IllegalArgumentException("상품을 구매한 회원만 리뷰를 작성할 수 있습니다.");
        }
    }

	// 해당 상품과 연관된 모든 리뷰를 가져오는 메서드(사진들을 함께 조회)
	public List<Review> getReviewsByProduct(Long pno) {
		//리뷰와 리뷰에 연관된 사진들을 함께 반환
        return reviewDao.findByPnoWithPhotos(pno);
    }
	
	public boolean isBuyer(String username, Long pno) {
	 // 해당 사용자의 주문 내역 확인
	    return orderDetailDao.isBuyer(username, pno);
	}

	public boolean isAdminUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication.getAuthorities().stream()
	            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	}
	
}