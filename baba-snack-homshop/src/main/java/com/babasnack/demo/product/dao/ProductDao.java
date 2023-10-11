package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.babasnack.demo.entity.Product;

@Repository
@Mapper
public interface ProductDao {
    // 상품목록
    @Select("SELECT * FROM product")
    public List<Product> findAllProducts();

    // 상품번호로 조회
    @Select("SELECT * FROM product WHERE pno = #{pno}")
    public Product findByProduct(Long pno);

    // 상품조회
    @Select("SELECT * FROM product WHERE product_name = #{productName}")
    public Product findByProductName(String productName);

    // 한 페이지당 상품 수
    @Select("SELECT * FROM (SELECT rownum as rnum,p.* FROM (SELECT /*+ index_desc(product_pk_pno)*/ * from 	product)p where rownum <=#{endRownum}) where rnum >=#{startRownum}") 
    public List<Product> findProductsByPage(@Param("startRownum") Long startRownum,@Param("endRownum") Long endRownum);
    
    @Select("select count(*) from products")
    public Long count();
}
