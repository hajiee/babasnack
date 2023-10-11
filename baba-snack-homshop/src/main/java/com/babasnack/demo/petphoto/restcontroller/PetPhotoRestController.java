package com.babasnack.demo.petphoto.restcontroller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.babasnack.demo.petphoto.service.PetPhotoService;

@RestController
public class PetPhotoRestController {
	@Autowired
	private PetPhotoService petPhotoService;
	
	@GetMapping(value="/profiles/{petImg}")
	public ResponseEntity<byte[]> viewProfile(@PathVariable String petImg){
		String parentDirectory = "c:/upload/petPhoto";
		File file = new File(parentDirectory, petImg);

		try {
			
			byte[] bytes = Files.readAllBytes(file.toPath());
			
			String contentType = Files.probeContentType(file.toPath()); 
			
			MediaType type = MediaType.parseMediaType(contentType);
			
			return ResponseEntity.ok().contentType(type).body(bytes);
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;	
	}
}
