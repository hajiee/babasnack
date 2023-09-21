package com.babasnack.demo.product.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dto.ProductPhotDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductPhotoService {
	@Autowired
    private ProductAdminDao productAdminDao;

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

	            ProductPhotDto.saveProductPhoto dto =
	                    new ProductPhotDto.saveProductPhoto(null, originalFilename, savedFilename, null);
	            
	            // dto 객체로부터 ProductPhoto 객체 생성
	            ProductPhoto productPhoto = new ProductPhoto();
	            productPhoto.setPno(pno);
	            productPhoto.setProductImg(dto.getProductImg());
	            productPhoto.setProductSaveImg(dto.getSavedFilename());

	            // 사진 정보를 DB에 저장합니다.
                productAdminDao.saveProductPhotos(Collections.singletonList(productPhoto));
	        }
	    }
	}

	public String saveFile(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String savedFileName = UUID.randomUUID().toString() + "." + extension;
		File destFile = new File(productSaveImg, savedFileName);

		try {
			file.transferTo(destFile);
			return savedFileName; // 실제로는 파일이름이나 경로 등으로 대체되어야 합니다.
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to save file: " + destFile.getAbsolutePath(), e);
		}
   }

	public void deleteFile(String filePath) throws Exception {
	    File deleteFile = new File(filePath);
	    if (deleteFile.exists()) {
	        try{
	        	deleteFile.delete();
	        	log.info("파일을 삭제하였습니다.");
	        }catch(Exception e){
	        	log.error("파일 삭제 중 오류가 발생했습니다.", e);
	        	throw e; // 예외 다시 던지기
	        }
	    } else {
	        log.info("파일이 존재하지 않습니다.");
	    }
   }

}