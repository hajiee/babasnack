package com.babasnack.demo.petphoto.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.babasnack.demo.petphoto.dao.PetPhotoDao;

@Service
public class PetPhotoService {
	@Autowired
	private PetPhotoDao petphotoDao;
	
	
	@Value("${upload.path}")
	private String uploadPath;
	// 프로필사진 업로드
    public void savePetPhoto(MultipartFile file, Long petpno) throws IOException {
        String fileName = petpno + "_" + System.currentTimeMillis() + ".jpg";
         Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	
    }
} 
	

