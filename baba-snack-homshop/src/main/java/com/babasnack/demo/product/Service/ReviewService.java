package com.babasnack.demo.product.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public void saveReview(ReviewDto.WritePR dto, List<MultipartFile> reviewUploadPhoto) throws IOException {
        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // ReviewDto에서 필요한 정보를 가져와서 Review 엔티티 생성
        Review review = Review.builder()
                .reviewDate(LocalDate.now())
                .reviewNotice(dto.getReviewNotice())
                .star(dto.getStar())
                .pno(dto.getPno())
                .reviewWrite(username)
                .build();

        // Review 엔티티 저장
        reviewDao.save(review);

        // 리뷰 사진 저장 로직
        List<ReviewPhoto> reviewPhotos = new ArrayList<>();
        for (MultipartFile photo : reviewUploadPhoto) {
            byte[] photoData = photo.getBytes();
            String originalFilename = photo.getOriginalFilename();

            String savedFilename = saveFile(photoData, originalFilename);

            ReviewPhoto reviewPhoto = ReviewPhoto.builder()
                    .reviewImg(originalFilename)
                    .reviewSaveImg(savedFilename)
                    .build();

            reviewPhotos.add(reviewPhoto);
        }

        // 리뷰 사진 엔티티 저장
        for (ReviewPhoto reviewPhoto : reviewPhotos) {
            reviewPhoto.setRno(review.getRno());
            reviewPhotoDao.saveReviewPhoto(reviewPhoto);
        }
    }

	public List<Review> getReviewsByProduct(Long pno) {
        return reviewDao.findByPnoWithPhotos(pno);
    }
	
	public boolean isBuyer(String username, Long pno) {
	    // 해당 사용자의 주문 내역 확인
	    boolean isBuyer = orderDetailDao.isBuyer(username, pno);
	    return isBuyer;
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
            outputStream = new FileOutputStream(savedFilename);
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
        String extension = extractFileExtension(originalFilename);
        String uniqueFileName = UUID.randomUUID().toString() + extension;
        return uniqueFileName;
    }

    private String extractFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1);
        }
        return ""; // 파일명에 확장자가 없는 경우를 처리하기 위한 부분
    }
}