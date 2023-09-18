package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dto.Category;

@Mapper
public interface ProductDao {
	// 모든 상품목록
    @Select("SELECT * FROM product")
    public List<Product> getAllProducts();
    
    // 상품조회
    @Select("SELECT * FROM product WHERE pno = #{id}")
    public Product getProductById(Long id);
    
    // 상품추가
    @Insert("INSERT INTO product (pno, product_name, product_notice, product_stock, product_price, product_size, reserve, product_cnt, category) " +
            "VALUES (product_seq.nextval, #{productName}, #{productNotice}, #{productStock}, #{productPrice}, #{productSize}, #{reserve, jdbcType=DOUBLE}, #{productCnt}, #{category.name})")
    @Options(useGeneratedKeys = true, keyProperty = "pno")
    public Product addProduct(Product product);
    
    // 상품수정
    @Update("UPDATE product SET product_name = #{productName}, product_notice = #{productNotice}, " +
            "product_stock = #{productStock}, product_price = #{productPrice}, product_size = #{productSize}, " +
            "reserve = #{reserve, jdbcType=DOUBLE}, product_cnt = #{productCnt}, category = #{category.name} " +
            "WHERE pno = #{pno}")
    public Product updateProduct(Product product);
    
    // 상품삭제
    @Delete("DELETE FROM product WHERE pno = #{id}")
    public Long deleteProduct(Long id);
    
	// 카테고리
	public List<Product> findByCategory(Category category);

}
