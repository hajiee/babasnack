package com.babasnack.demo.product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dto.Category;

@Service
public class ProductService {
	@Autowired
    private ProductDao productDao; // ProductRepository는 데이터베이스 접근을 위한 인터페이스 또는 클래스

    public List<Product> getCatProducts(Category cat) {
        // 'CAT' 카테고리에 해당하는 상품 목록 조회
        return productDao.findByCategory(Category.CAT);
    }

    public List<Product> getDogProducts(Category dog) {
        // 'DOG' 카테고리에 해당하는 상품 목록 조회
        return productDao.findByCategory(Category.DOG);
    }

	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

}
