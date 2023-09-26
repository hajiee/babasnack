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
	@Value("${profileFolder}")
	private String profileFolder;
	@Value("${defaultProfile}")
	private String defaultProfile;
	@Value("${profileUrl}")
	private String profileUrl;
	@Value("${upload.path}")
	private String uploadPath;
	// 프로필사진 업로드
    public void savePetPhoto(MultipartFile file, Long petpno) throws IOException {
        String fileName = petpno + "_" + System.currentTimeMillis() + ".jpg";
         Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	
    }
    // 프로필 사진 변경
    public void changePetPhoto(MultipartFile file, Long petpno) throws IOException {
        String fileName = petpno + "_" + System.currentTimeMillis() + ".jpg";
        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 예시로서 PetPhotoDao의 PsChangePhoto 메서드를 호출하도록 작성합니다.
        petphotoDao.PsChangePhoto(fileName, petpno);
	}
}
	
 
	

