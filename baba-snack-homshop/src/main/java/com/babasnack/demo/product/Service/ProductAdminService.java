package com.babasnack.demo.product.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dao.ProductDao;

@Service
public class ProductAdminService {
	 @Autowired
	    private ProductDao productDao; // ProductDao는 MyBatis를 사용한 데이터 액세스 인터페이스

	    public void addProduct(Product product) {
	        // 상품 추가 로직
	        productDao.addProduct(product);
	    }

	    public void updateProduct(Product product) {
	        // 상품 수정 로직
	        productDao.updateProduct(product);
	    }

	    public void deleteProduct(Long id) {
	        // ProductService에서 상품 및 관련된 사진과 리뷰 삭제 로직
	        productDao.deleteProduct(id);
	        // 추가로 사진 및 리뷰 삭제 로직을 구현할 수 있음
	    }

}
