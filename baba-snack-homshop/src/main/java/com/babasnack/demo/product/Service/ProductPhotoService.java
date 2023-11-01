package com.babasnack.demo.product.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.error.Exception.FileSaveException;
import com.babasnack.demo.product.dao.ProductPhotoDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductPhotoService {
    @Autowired
    private ProductPhotoDao productPhotoDao;

    @Value("${defaultproductSaveImg}")
    private String defaultProductSaveImg;

    @Value("${productSaveImg}")
    private String productSaveImg;

    @Value("${productImgUrl}")
    private String productImgUrl;

    public void saveProductPhotos(Long pno, MultipartFile photo) {
        if (!photo.isEmpty()) {
            String savedFilename = saveFile(photo);

            ProductPhoto productPhoto = new ProductPhoto();
            productPhoto.setPno(pno);
            productPhoto.setProductSaveImg(savedFilename);

            // 고유한 번호(productImgNo) 생성 및 할당
            Long productImgNo = generateUniqueNumber();
            productPhoto.setProductImgNo(productImgNo);

            // ProductPhoto 객체에 Product 객체 설정
            Product product = new Product();
            product.setPno(pno);
            productPhoto.setProduct(product);

            // 사진 정보를 DB에 저장합니다.
            productPhotoDao.saveProductPhoto(productPhoto);
        }
    }

	public String saveFile(MultipartFile file) {
		try {
	        Path uploadPath = Paths.get(productSaveImg);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
	        Path filePath = Paths.get(productSaveImg, uniqueFileName);
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
	    Path fileToDelete = Paths.get(productSaveImg, filePath);
	    try {
	        Files.deleteIfExists(fileToDelete);
	        log.info("파일을 삭제하였습니다.");
	    } catch (IOException e) {
	        log.error("파일 삭제 중 오류가 발생했습니다.", e);
	        throw new Exception("Failed to delete the file", e);
	    }
	}
	
    public String getFilePath(String filename) {
        return Paths.get(productSaveImg, filename).toString();
    }

}