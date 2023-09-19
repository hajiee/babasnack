package com.babasnack.demo.petservice;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetService {
	@Value("${upload.path}")
	private String uploadPath;
	
	public void PetSaveImg(MultipartFile file,String petname)throws IOException{
		 String fileName = petname + "_" + System.currentTimeMillis() + ".jpg";
	        File saveFile = new File(uploadPath + File.separator + fileName);
	        file.transferTo(saveFile);
	}

	
}
