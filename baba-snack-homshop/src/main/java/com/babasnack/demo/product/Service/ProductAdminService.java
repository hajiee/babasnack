package com.babasnack.demo.product.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Transactional
    public Long addProduct(ProductDto.WriteP productDto, List<MultipartFile> uploadedPhotos) {
        Product product = productDto.toProduct();
        Long newProductId = productAdminDao.addProduct(product);

    	List<ProductPhoto> photos = new ArrayList<>();

        for (MultipartFile uploadedPhoto : uploadedPhotos) {
            if (!uploadedPhoto.isEmpty()) {
                String originalFilename = uploadedPhoto.getOriginalFilename();
                String savedFilename = productPhotoService.saveFile(uploadedPhoto);

                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProductImg(originalFilename);
                productPhoto.setProductSaveImg(savedFilename);
                photos.add(productPhoto);
            }
        }
        
        for (ProductPhoto photo : photos) {
            photo.setPno(newProductId);
            productPhotoDao.saveProductPhoto(photo);
        }

        return newProductId;
    }

	// 상품 수정
	@Transactional
	public Long updateProduct(Long pno, ProductDto.WriteP productDto, List<ProductPhoto> photos) {
        // 기존 상품 정보 조회
        Product existingProduct = productAdminDao.findByProduct(pno);
        if (existingProduct == null) {
            throw new RuntimeException(pno+"상품정보를 읽어오지 못했습니다");
        }

        // 상품 정보 업데이트
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setProductNotice(productDto.getProductNotice());
        existingProduct.setProductStock(productDto.getProductStock());
        existingProduct.setProductPrice(productDto.getProductPrice());
        existingProduct.setProductSize(productDto.getProductSize());
        existingProduct.setCategory(productDto.getCategory());

        Long updatedProductId = productAdminDao.updateProduct(existingProduct);

        // 등록된 상품의 사진 삭제
        deleteAllPhotosByPno(pno);

        // 새로운 사진 등록 및 연관성 설정
        for (ProductPhoto photo : photos) {
            photo.setPno(pno);
            productPhotoDao.saveProductPhoto(photo);
        }

        return updatedProductId;
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
        List<ProductPhoto> photos = productPhotoDao.getProductPhotosByPNo(pno);
        for (ProductPhoto photo : photos) {
            String savedFilename = photo.getProductSaveImg();
            String filePath = productSaveImg + "/" + savedFilename;
            try {
                productPhotoService.deleteFile(filePath);
            } catch (Exception e) {
                throw new RuntimeException("파일 시스템에서 사진을 삭제하는 데 실패했습니다.", e);
            }
            Long deletedCount = productPhotoDao.deleteProductPhoto(photo.getProductImgNo());
            if (deletedCount == 0) {
                throw new RuntimeException("데이터베이스에서 사진을 삭제하는 데 실패했습니다.");
            }
        }
    }

    private void deleteFile(String filePath) {
        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            boolean isDeleted = fileToDelete.delete();

            if (!isDeleted) {
                throw new RuntimeException("Failed to delete the file: " + filePath);
            }
        } else {
            throw new IllegalArgumentException("The file does not exist: " + filePath);
        }
    }

    private List<ProductPhoto> getProductPhotosByPNo(Long pno) {
        return productPhotoDao.getProductPhotosByPNo(pno);
    }

    public String getSavedFilename(String productSaveImg) {
        return productSaveImg;
    }

    private String getFilePath(String filename) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String filePath = "/path/to/files/" + year + "/" + month + "/" + filename;
        return filePath;
    }

    private Long deleteFromDatabase(Long photoImgNo) {
        return this.productPhotoDao.deleteProductPhoto(photoImgNo);
    }

    // 모든 상품 조회
    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }

    // 특정 상품 조회
    public Product getProductById(Long pno) {
        return productAdminDao.findByProduct(pno);
    }
}
