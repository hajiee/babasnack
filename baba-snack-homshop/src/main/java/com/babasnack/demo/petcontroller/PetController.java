package com.babasnack.demo.petcontroller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.petservice.PetService;





@Controller
public class PetController {
	
	
	//펫프로필 사진 업로드
	@Autowired
    private PetService petService;
	@PostMapping("/uploadPetImg")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
        try {
            petService.PetSaveImg(file, username);
            return ResponseEntity.ok("Profile uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading profile.");
        }
    }
}