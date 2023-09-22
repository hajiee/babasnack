package com.babasnack.demo.product.Service;

import java.io.FileOutputStream;
import java.io.IOException;
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
import com.babasnack.demo.product.dao.ReviewDao;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;

	public void saveReview(Review review) {
		// 리뷰 저장 로직 추가
		reviewDao.save(review);
	}

	public boolean isBuyer(String username, Long pno) {
		// 해당 사용자가 상품을 구매했는지 확인하는 로직 추가 (예: Order 테이블과 연동하여 확인)
		return true; // 임시로 true 반환하도록 설정됨. 실제 코드에서는 해당 기능을 구현해야 함.
	}

	public boolean isAdminUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	}

	public void saveReviewWithPhoto(Review review, MultipartFile photo) throws IOException {
		// 리뷰 사진 저장 로직 추가
		if (!photo.isEmpty()) {
			byte[] photoData = photo.getBytes();
			String filename = photo.getOriginalFilename();

			// 파일 저장 등의 처리를 수행한다.
			String savedFilename = saveFile(photoData, filename); // 파일을 실제로 저장하고, 저장된 파일명을 반환

			// 리뷰 사진 객체 생성
			ReviewPhoto reviewPhoto = new ReviewPhoto();
			reviewPhoto.setReviewImg(filename);
			reviewPhoto.setReviewSaveImg(savedFilename);

			// 리뷰 객체에 사진 정보 설정
			List<ReviewPhoto> photos = new ArrayList<>();
			photos.add(reviewPhoto);
			review.setPhotos(photos);
		}
		// 리뷰 저장
		saveReview(review);
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
	    return "";	//파일명에 확장자가 없는 경우를 처리하기 위한 부분
	}
}
