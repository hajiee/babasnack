package com.babasnack.demo.product.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.error.Exception.FileSaveException;
import com.babasnack.demo.product.dao.ReviewDao;
import com.babasnack.demo.product.dao.ReviewPhotoDao;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ReviewPhotoService {
	@Autowired
	private ReviewPhotoDao reviewPhotoDao;
	@Autowired
	private ReviewDao reviewDao;
	
	@Value("${defaultreviewSaveImg}")
	private String defaultreviewSaveImg;
	@Value("${reviewSaveImg}")
	private String reviewSaveImg;
	@Value("${reviewImgUrl}")
	private String reviewImgUrl;

	public void saveReviewPhoto(Long rno, MultipartFile revPhoto) {
        if (!revPhoto.isEmpty()) {
            String savedFilename = saveFile(revPhoto);

            ReviewPhoto reviewPhoto = new ReviewPhoto();
            reviewPhoto.setRno(rno);
            reviewPhoto.setReviewSaveImg(savedFilename);

            // 고유한 번호(reviewImgNo) 생성 및 할당
            Long reviewImgNo = generateUniqueNumber();
            reviewPhoto.setReviewImgNo(reviewImgNo);

            // 사진 정보를 DB에 저장합니다.
            reviewPhotoDao.saveReviewPhoto(reviewPhoto);
        }
    }
	
	public String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(reviewSaveImg);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            Path filePath = Paths.get(reviewSaveImg, uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            return uniqueFileName;
        } catch (IOException e) {
            throw new FileSaveException("Failed to save file", e);
        }
    }

	private String generateUniqueFileName(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename); // 파일 확장자 추출
        String uniqueId = UUID.randomUUID().toString(); // 고유한 식별자 생성

        return uniqueId + "." + extension; // 고유한 식별자와 확장자를 조합하여 새로운 파일명 생성
    }

    private Long generateUniqueNumber() {
        // UUID를 사용하여 임의의 8자리 숫자를 생성하는 예시입니다.
        String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
        long uniqueNumber = Long.parseLong(uniqueId.substring(0, 8), 16);

        return uniqueNumber;
    }

    public void deleteFile(String filePath) throws Exception {
        Path fileToDelete = Paths.get(reviewSaveImg, filePath);
        try {
            Files.deleteIfExists(fileToDelete);
            log.info("파일을 삭제하였습니다.");
        } catch (IOException e) {
            log.error("파일 삭제 중 오류가 발생했습니다.", e);
            throw new Exception("Failed to delete the file", e);
        }
    }

    public String getFilePath(String filename) {
        return Paths.get(reviewSaveImg, filename).toString();
    }
    
	public List<ReviewPhoto> findReviewPhotosByRno(Long rno) {
		// 특정 리뷰에 연관된 사진들 조회 로직
		return reviewDao.findReviewPhotosByRno(rno);
	}
}
