package com.babasnack.demo.petphoto.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.petphoto.service.PetPhotoService;

@RestController
public class PetPhotoController {
    
    private final PetPhotoService petphotoService;
    
    public PetPhotoController(PetPhotoService petphotoService) {
        this.petphotoService = petphotoService;
    }
    
    @GetMapping("/getPetImg")
    public ResponseEntity<byte[]> getProfile(@RequestParam("petprono") Long petprono) {
        byte[] photo = petphotoService.getPetPhoto(petprono);
        if (photo != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("image/jpeg"));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .contentLength(photo.length)
                    .body(photo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/member/uploadPetPhoto")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("petprono") Long petprono) {
        try {
            petphotoService.savePetPhoto(file, petprono);
            return ResponseEntity.ok("Pet photo uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading pet photo.");
        }
    }

    @PostMapping("/changePetImg")
    public ResponseEntity<String> changeProfile(@RequestParam("file") MultipartFile file, @RequestParam("petprono") Long petprono) {
        try {
            petphotoService.savePetPhoto(file, petprono);
            return ResponseEntity.ok("Profile changed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error changing profile.");
        }
    }
}
