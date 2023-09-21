package com.babasnack.demo.product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dto.ProductDto;

@Service
public class ProductAdminService {
	@Autowired
	private ProductAdminDao productAdminDao;
	@Autowired
	private ProductDao productDao;

	@Transactional
	public Long addProduct(ProductDto.WriteP productDto, List<ProductPhoto> photos) {
		// 상품 정보 등록
		Long productID = productAdminDao.addProduct(productDto);

		// 등록된 상품의 ID를 사용하여 사진 등록
		for (ProductPhoto photo : photos) {
			photo.setPno(productID);
			productAdminDao.saveProductPhoto(photo);
		}
		return productID;
	}

	// 상품 수정
	@Transactional
	public Long updateProduct(Long pno, ProductDto.WriteP productDto, List<ProductPhoto> photos) {
		// 상품 정보 업데이트
		Long upProductId = productAdminDao.updateProduct(productDto);

		// 등록된 상품의 사진 정보 업데이트
		productAdminDao.deleteProductPhotos(pno);
		// 기존 사진 삭제
		for (ProductPhoto photo : photos) {
			photo.setPno(pno);
			productAdminDao.saveProductPhoto(photo);
		}
		return upProductId;
	}

	// 상품삭제
	@Transactional
	public void deleteProduct(Long pno) {
		// 상품에 연관된 사진 삭제
		productAdminDao.deleteProductPhotos(pno);
		// 상품 정보 삭제
		productAdminDao.deleteProduct(pno);
	}

	// 모든 상품 조회
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	// 특정 상품 조회
	public Product getProductById(Long pno) {
		return productAdminDao.findByProduct(pno);
	}
}
