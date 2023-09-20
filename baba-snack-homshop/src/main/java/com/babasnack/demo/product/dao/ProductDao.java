package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dto.ProductDto;

@Repository
@Mapper
public interface ProductDao {
	// 상품목록
	@Select("SELECT * FROM product")
    public List<Product> findAll();

	// 상품조회
    @Select("SELECT * FROM product WHERE productName = #{productName}")
    public Product findByProductName(String productName);

    // 한 페이지당 상품 수
    @Select("SELECT * FROM (SELECT rownum as rnum, p.* FROM (SELECT /*+ index_desc(product product_pk_pno)*/ * FROM product) p WHERE rownum <= #{endRownum}) WHERE rnum >= #{startRownum}")
    public List<ProductDto.ListP> findPageOne(Long startRownum, Long endRownum);
}
