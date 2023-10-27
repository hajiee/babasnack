package com.babasnack.demo.petphoto.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.petphoto.service.PetPhotoService;


@Controller
public class PetPhotoController {
    @Autowired
    private PetPhotoService petphotoService;

    // 펫프로필 사진 업로드
    @PostMapping("/uploadPetImg")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("petpno") Long petpno) {
        try {
            petphotoService.savePetPhoto(file, petpno);
            return ResponseEntity.ok("Pet photo uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading pet photo.");
        }
    }

    @PostMapping("/changePetImg")
    public ResponseEntity<String> changeProfile(@RequestParam("file") MultipartFile file, @RequestParam("petpno") Long petpno) {
        try {
            petphotoService.savePetPhoto(file, petpno);
            return ResponseEntity.ok("Profile changed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error changing profile.");
        }
    }
}






   
