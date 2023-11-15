package com.babasnack.demo.product.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.orderdetail.dao.OrderDetailDao;
import com.babasnack.demo.product.dao.ReviewDao;
import com.babasnack.demo.product.dao.ReviewPhotoDao;
import com.babasnack.demo.product.dto.ReviewDto;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ReviewPhotoDao reviewPhotoDao;
	@Autowired
	private OrderDetailDao orderDetailDao;

	public void saveReview(ReviewDto.WritePR dto, List<MultipartFile> reviewUploadPhoto, Authentication authentication) throws IOException {
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
	        reviewDao.save(review);

	        // 리뷰 사진 저장 로직
	        List<ReviewPhoto> reviewPhotos = new ArrayList<>();
	        for (MultipartFile revPhoto : reviewUploadPhoto) {
	            if (revPhoto != null) { // photo가 null이 아닌 경우에만 처리
	                byte[] photoData = revPhoto.getBytes();
	                String originalFilename = revPhoto.getOriginalFilename();

	                String savedFilename = saveFile(photoData, originalFilename);

	                ReviewPhoto reviewPhoto = ReviewPhoto.builder()
	                        .reviewImg(originalFilename)
	                        .reviewSaveImg(savedFilename)
	                        .build();

	                reviewPhotos.add(reviewPhoto);
	            }
	        }

	        // 리뷰 사진 엔티티 저장
	        for (ReviewPhoto reviewPhoto : reviewPhotos) {
	            reviewPhoto.setRno(review.getRno());
	            reviewPhotoDao.saveReviewPhoto(reviewPhoto);
	        }
	    } else {
	        throw new IllegalArgumentException("상품을 구매한 회원만 리뷰를 작성할 수 있습니다.");
	    }
	}

	public List<Review> getReviewsByProduct(Long pno) {
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


	private String saveFile(byte[] fileData, String originalFilename) throws IOException {
        String savedFilename = generateUniqueFileName(originalFilename); // 중복되지 않는 고유한 파일명 생성

        FileOutputStream outputStream = null;

        try {
            String filePath = "/path/to/save/" + savedFilename; // 파일을 저장할 경로 지정

            outputStream = new FileOutputStream(filePath);
            outputStream.write(fileData);

            // 필요한 경우 추가적인 작업 수행 가능

            return savedFilename;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // 오류 처리 로직 추가
                }
            }
        }
    }

	private String generateUniqueFileName(String originalFilename) {
	    String extension = FilenameUtils.getExtension(originalFilename);
	    String uniqueFileName = UUID.randomUUID().toString() + (extension != null ? "." + extension : "");
	    return uniqueFileName;
	}
}