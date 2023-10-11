package com.babasnack.demo.product.Service;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dto.ProductDto;

@Service
public class ProductAdminService {
    private final ProductAdminDao productAdminDao;
    private final ProductDao productDao;
    private final ProductPhotoDao productPhotoDao;

    @Autowired
    public ProductAdminService(ProductAdminDao productAdminDao, ProductDao productDao, ProductPhotoDao productPhotoDao) {
        this.productAdminDao = productAdminDao;
        this.productDao = productDao;
        this.productPhotoDao = productPhotoDao;
    }
    
	@Transactional
	public Long addProduct(ProductDto.WriteP productDto, List<ProductPhoto> photos) {
		// 상품 정보 등록
		Long productID = productAdminDao.addProduct(productDto);

		// 등록된 상품의 ID를 사용하여 사진 등록
		for (ProductPhoto photo : photos) {
			photo.setPno(productID);
			productPhotoDao.saveProductPhoto(photo);
		}
		return productID;
	}

	// 상품 수정
	@Transactional
	public Long updateProduct(Long pno, ProductDto.WriteP productDto, List<ProductPhoto> photos) {
		// 상품 정보 업데이트
		Long upProductId = productAdminDao.updateProduct(productDto);

		// 등록된 상품의 사진 삭제 및 새로운 사진 등록 및 연관성 설정
		deleteAllPhotosByPno(pno);
		for (ProductPhoto photo : photos) {
			photo.setPno(pno);
			productPhotoDao.saveProductPhoto(photo);
		}

		return upProductId;
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
		List<ProductPhoto> photos = getProductPhotosByPNo(pno);

		for (ProductPhoto photo : photos) {
			String savedFilename = photo.getSavedFilename();
			String filePath = getFilePath(savedFilename);
			deleteFile(filePath);

			Long deletedCount = deleteFromDatabase(photo.getProductImgNo());
			if (deletedCount == null || deletedCount == 0L) {
				throw new RuntimeException("Failed to delete the photo from the database");
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
