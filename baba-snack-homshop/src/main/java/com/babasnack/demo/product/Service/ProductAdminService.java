package com.babasnack.demo.product.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dto.ProductDto;

@Service
public class ProductAdminService {
    @Autowired
    private ProductAdminDao productAdminDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductPhotoDao productPhotoDao;
    @Autowired
    private ProductPhotoService productPhotoService;

    @Value("${productSaveImg}")
    private String productSaveImg;

    // 상품 등록
    @Transactional
    public Long addProduct(ProductDto.WriteP productDto, List<MultipartFile> uploadedPhotos) {
        Product product = productDto.toProduct();
        product.setProductDay(LocalDate.now()); // 현재 날짜로 설정
        product.calculateReserve(); // 적립금 계산 및 설정
        productAdminDao.addProduct(product);
        
        for (MultipartFile uploadedPhoto : uploadedPhotos) {
            if (!uploadedPhoto.isEmpty()) {
                String originalFilename = uploadedPhoto.getOriginalFilename();
                String savedFilename = productPhotoService.saveFile(uploadedPhoto);

                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProductImg(originalFilename);
                productPhoto.setProductSaveImg(savedFilename);
                productPhoto.setPno(product.getPno()); // 상품 번호 설정
                productAdminDao.addProductPhoto(productPhoto); // 사진 정보 등록
            }
        }
		
        return product.getPno();
    }

    // 상품 수정
    @Transactional
    public Long updateProduct(Long pno, ProductDto.WriteP productDto, List<MultipartFile> uploadedPhotos) {
        // 기존 상품 정보 조회
        Product existingProduct = productDao.findByProduct(pno);
        if (existingProduct == null) {
            throw new RuntimeException(pno + "번 상품 정보를 읽어오지 못했습니다.");
        }

        // 상품 정보 업데이트
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setProductNotice(productDto.getProductNotice());
        existingProduct.setProductStock(productDto.getProductStock());
        existingProduct.setProductPrice(productDto.getProductPrice());
        existingProduct.setProductSize(productDto.getProductSize());
        existingProduct.setCategory(productDto.getCategory());

        productAdminDao.updateProduct(existingProduct);

        // 등록된 상품의 사진 삭제
        deleteAllPhotosByPno(pno);

        // 새로운 사진 등록
        for (MultipartFile uploadedPhoto : uploadedPhotos) {
            if (!uploadedPhoto.isEmpty()) {
                String originalFilename = uploadedPhoto.getOriginalFilename();
                String savedFilename = productPhotoService.saveFile(uploadedPhoto);

                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProductImg(originalFilename);
                productPhoto.setProductSaveImg(savedFilename);
                productPhoto.setPno(pno); // 상품 번호 설정
                productAdminDao.addProductPhoto(productPhoto); // 사진 정보 등록
            }
        }

        return pno;
    }

    @Transactional
    public void deleteProduct(Long pno) {
        // 상품에 연관된 모든 사진 삭제
        deleteAllPhotosByPno(pno);

        // 상품 정보 삭제
        int deletedCount = productAdminDao.deleteProduct(pno);
        if (deletedCount == 0) {
            throw new RuntimeException("Failed to delete the product from the database");
        }
    }

    private void deleteAllPhotosByPno(Long pno) {
        // 상품에 연관된 모든 사진 가져오기
        List<ProductPhoto> photos = productPhotoDao.getProductPhotosByPNo(pno);
        
        // 각 사진에 대해 삭제 수행
        for (ProductPhoto photo : photos) {
            String savedFilename = photo.getProductSaveImg();
            String filePath = productSaveImg + "/" + savedFilename;
            
            try {
                productPhotoService.deleteFile(filePath);
            } catch (Exception e) {
                throw new RuntimeException("파일 시스템에서 사진을 삭제하는 데 실패했습니다.", e);
            }
        }
        
        // 상품에 연관된 모든 사진 삭제
        Long deletedCount = productAdminDao.deleteAllPhotosByPNo(pno);
        if (deletedCount == 0) {
            throw new RuntimeException("데이터베이스에서 사진을 삭제하는 데 실패했습니다.");
        }
    }

    public String getSavedFilename(String productSaveImg) {
        return productSaveImg;
    }
    
    // 모든 상품 조회
    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }
    
    // 상품목록 최신등록 순
    public List<Product> getAllProductsSortedByDate() {
        return productDao.findAllProductsOrderByRegistrationDate();
    }

    // 특정 상품 조회
    public Product getProductById(Long pno) {
        return productDao.findByProduct(pno);
    }
}
