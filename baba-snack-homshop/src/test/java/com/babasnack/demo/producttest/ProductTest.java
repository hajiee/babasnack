package com.babasnack.demo.producttest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dto.Category;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductDao productDao;

    @Test
    @Transactional
    @Rollback(true) // 테스트 종료 후 롤백
    public void testAddProduct() {
        // 새로운 상품 생성
        Product product = new Product();
        product.setProductName("New Product");
        product.setProductNotice("Product Notice");
        product.setProductStock(10L);
        product.setProductPrice(1000L);
        product.setProductSize(20L);
        product.setCategory(Category.CAT);

        // 상품 추가
        productDao.addProduct(product);

        // 상품 추가 후 조회
        Product addedProduct = productDao.getProductById(product.getPno());
        assertNotNull(addedProduct); // 상품이 정상적으로 추가되었는지 확인
        assertEquals("New Product", addedProduct.getProductName()); // 상품 이름 확인
    }

    @Test
    @Transactional
    @Rollback(true) // 테스트 종료 후 롤백
    public void testGetAllProducts() {
        List<Product> products = productDao.getAllProducts();
        assertNotNull(products); // 상품 목록이 null이 아닌지 확인
    }
}
