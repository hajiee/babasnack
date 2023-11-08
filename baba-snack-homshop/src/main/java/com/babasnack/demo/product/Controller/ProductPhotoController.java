package com.babasnack.demo.product.Controller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.product.Service.ProductPhotoService;

import lombok.extern.log4j.Log4j2;

@RequestMapping
@Log4j2
@Controller
public class ProductPhotoController {
    @Autowired
    private ProductPhotoService productPhotoService;

    @PostMapping("/product/{pno}/photos")
    public String saveProductPhotos(@PathVariable Long pno, @RequestParam("photos") MultipartFile[] photos, Model model) throws Exception {
        String[] messages = new String[photos.length];
        int messageIndex = 0;

        for (MultipartFile photo : photos) {
            if (!photo.isEmpty()) {
                try {
                    // 사진 정보를 DB에 저장합니다.
                    productPhotoService.saveProductPhotos(pno, photo);
                    messages[messageIndex] = "파일 " + photo.getOriginalFilename() + "을(를) 저장했습니다.";
                } catch (Exception e) {
                    log.error("Failed to save file: " + photo.getOriginalFilename(), e);
                    messages[messageIndex] = "파일 " + photo.getOriginalFilename() + "을(를) 저장하는 도중 오류가 발생했습니다.";
                }

                messageIndex++;
            }
        }

        model.addAttribute("messages", messages);

        // 내용이 비어있는 경우 에러 처리 및 페이지 반환
        if (messageIndex == 0) {
            model.addAttribute("error", "대표사진이 비어있습니다.");
            return "product/product-write";
        }

        return "result"; // 결과 페이지로 이동
    }

}