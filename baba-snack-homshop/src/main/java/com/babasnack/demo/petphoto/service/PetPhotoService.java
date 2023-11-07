package com.babasnack.demo.petphoto.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.PetPhoto;
import com.babasnack.demo.petphoto.dao.PetPhotoDao;

@Service
public class PetPhotoService {
    @Autowired
    private PetPhotoDao petphotoDao;

    @Value("${upload.path}")
    private String uploadPath;

    // 프로필 사진 업로드
    public void savePetPhoto(MultipartFile file, Long petprono) throws IOException {
        String fileName = petprono + "_" + System.currentTimeMillis() + ".jpg";
        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        PetPhoto petphoto = new PetPhoto();
        petphoto.setPetName(fileName);
        petphoto.setPetProno(petprono);
        petphotoDao.savePetPhoto(petphoto);
    }

    // 프로필 사진 변경
    public void changePetPhoto(MultipartFile file, Long petprono) throws IOException {
        String fileName = petprono + "_" + System.currentTimeMillis() + ".jpg";
        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        petphotoDao.PsChangePhoto(fileName, petprono);
    }
 // 펫 사진 조회
    public byte[] getPetPhoto(Long petpno) {
        // 펫 사진을 조회하는 로직을 구현하세요.
        // 예시로 byte 배열을 생성하여 반환합니다. 실제로는 데이터베이스나 파일 시스템 등에서 펫 사진을 가져와야 합니다.

        // 예시: 펫 사진을 생성하여 반환
        try {
            BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 200, 200);
            graphics.setColor(Color.BLACK);
            graphics.drawString("Pet Photo", 50, 100);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
