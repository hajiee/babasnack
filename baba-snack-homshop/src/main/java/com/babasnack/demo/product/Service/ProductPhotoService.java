package com.babasnack.demo.product.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.error.Exception.FileSaveException;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dto.ProductPhotDto;

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

	public void saveProductPhotos(Long pno, MultipartFile[] photos) {
	    for (MultipartFile photo : photos) {
	        if (!photo.isEmpty()) {
	            String originalFilename = photo.getOriginalFilename();
	            String savedFilename = saveFile(photo);

	            ProductPhotDto.addProductPhoto dto =
	                    new ProductPhotDto.addProductPhoto(null, originalFilename, savedFilename, null);
	            
	            // dto 객체로부터 ProductPhoto 객체 생성
	            ProductPhoto productPhoto = new ProductPhoto();
	            productPhoto.setPno(pno);
	            productPhoto.setProductImg(dto.getProductImg());
	            productPhoto.setProductSaveImg(dto.getSavedFilename());

	            // 사진 정보를 DB에 저장합니다.
	            productPhotoDao.saveProductPhoto(productPhoto);
	        }
	    }
	}

	public String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(productSaveImg);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(productSaveImg, uniqueFileName);
            Files.copy(file.getInputStream(), filePath);
            return uniqueFileName;
        } catch (IOException e) {
            throw new FileSaveException("Failed to save file", e);
        }
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