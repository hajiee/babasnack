package com.babasnack.demo.product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dto.ProductDto;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

	// 상품 목록 조회 서비스 메서드
	public List<Product> getProductList() {
		return productDao.findAll();
	}

	// 특정 상품 조회 서비스 메서드 - 만약 해당하는 상품이 없다면 null이 반환
	public Product getProductByProductName(String productName) {
		return productDao.findByProductName(productName);
	}

	// 한 페이지당 상품 수 조회 서비스 메서드
	public List<ProductDto.ListP> getPageOne(Long startRownum, Long endRownum) {
	    return productDao.findPageOne(startRownum, endRownum);
	}
	
	// 특정 상품의 상세 정보 조회 서비스 메서드
	public ProductDto.ReadP getProductDetail(String productName) {
	    Product product = getProductByProductName(productName);

	    if (product == null) { 
	        throw new NotFoundException("Product not found."); 
	    }
	    
	    ProductDto.ReadP detail = new ProductDto.ReadP();
	    detail.setProductName(product.getProductName());
	    detail.setProductNotice(product.getProductNotice());
	    detail.setProductPrice(product.getProductPrice());
	    detail.setProductSize(product.getProductSize());
	    
	    // 나머지 필드 설정 (productCnt, reviews 등)
	    
	    return detail;
		}
}
