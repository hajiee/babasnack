package com.babasnack.demo.product.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dto.ProductPhotDto;

import lombok.extern.java.Log;

@Log
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

                ProductPhotDto.saveProductPhoto dto = new ProductPhotDto.saveProductPhoto(null, originalFilename, savedFilename, null);
                
                // 사진 정보를 DB에 저장합니다.
                productAdminDao.saveProductPhotos(null);
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

	// 파일이 저장된 경로를 이용하여 파일 객체를 생성하고 해당 파일이 존재하면 파일을 삭제
	public void deleteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath);
		if (deleteFile.exists()) {
			deleteFile.delete();
			log.info("파일을 삭제하였습니다.");
		} else {
			log.info("파일이 존재하지 않습니다.");
		}
	}

}