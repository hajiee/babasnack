package com.babasnack.demo.product.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.product.dto.ProductDto;

@Mapper
public interface ProductAdminDao {
	// 상품등록
	@Insert("INSERT INTO product (productName, productNotice, productStock, productPrice, productSize) " +
	        "VALUES (#{product.productName}, #{product.productNotice}, #{product.productStock}, " +
	        "#{product.productPrice}, #{product.productSize})")
	@Options(useGeneratedKeys = true, keyProperty = "product.pno")	//MyBatis에서 자동 생성된 키(auto-generated key) 값을 사용하기 위한 옵션
	public Long addProduct(ProductDto.WriteP productDto);

	// 상품수정
	@Update("UPDATE product SET productName = #{product.productName}, " +
	        "productNotice = #{product.productNotice}, " +
	        "productStock = #{product.productStock}, " +
	        "productPrice = #{product.productPrice}, " +
	        "prodcutSize = #{prodcut.prodcutSize} WHERE pno = #{pno}")
	public Long updateProduct(ProductDto.WriteP prodcutDto);
	
	// 상품삭제
    @Delete("DELETE FROM product WHERE pno = #{pno}")
    public Boolean deleteProduct(Long pno);
}
