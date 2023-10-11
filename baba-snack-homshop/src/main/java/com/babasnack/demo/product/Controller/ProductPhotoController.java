package com.babasnack.demo.product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.Service.ProductPhotoService;
import com.babasnack.demo.product.dao.ProductPhotoDao;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductPhotoController {
    @Autowired
    private ProductPhotoService productPhotoService;
    @Autowired
    private ProductPhotoDao productPhotoDao;
    

    @PostMapping("/product/{pno}/photos")
    public String saveProductPhotos(@PathVariable Long pno, @RequestParam("photos") MultipartFile[] photos,
            Model model) throws Exception {
        String[] messages = new String[photos.length];
        int messageIndex = 0;

        for (MultipartFile photo : photos) {
            if (!photo.isEmpty()) {
                String originalFilename = photo.getOriginalFilename();
                String savedFilename = productPhotoService.saveFile(photo);

                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setPno(pno);
                productPhoto.setProductImgNo(null); // 이 부분은 필요에 따라 수정해야 합니다.
                productPhoto.setProductImg(originalFilename);
                productPhoto.setProductSaveImg(savedFilename);

    			try {
    				// 사진 정보를 DB에 저장합니다.
    				productPhotoDao.saveProductPhoto(productPhoto);
                    messages[messageIndex] = "파일 " + savedFilename + "을(를) 저장했습니다.";
    			}  catch (Exception e) {
    				productPhotoService.deleteFile(originalFilename);
    				productPhotoService.deleteFile(savedFilename);
                    log.error("Failed to save file: " + savedFilename, e);
                    messages[messageIndex] = "파일 " + savedFilename + "을(를) 저장하는 도중 오류가 발생했습니다.";
    			}
    			
    			messageIndex++;
    		}
    	}

        model.addAttribute("messages", messages);

        return "result"; // 결과 페이지로 이동하도록 설정해야 합니다.
    }
    
}