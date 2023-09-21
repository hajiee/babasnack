package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dto.ProductDto;

@Mapper
public interface ProductAdminDao {
	// 상품등록
	@Insert("INSERT INTO product (productName, productNotice, productStock, productPrice, productSize, category) " +
	        "VALUES (#{product.productName}, #{product.productNotice}, #{product.productStock}, " +
	        "#{product.productPrice}, #{product.productSize}, #{category})")
	@Options(useGeneratedKeys = true, keyProperty = "product.pno")	//MyBatis에서 자동 생성된 키(auto-generated key) 값을 사용하기 위한 옵션
	public Long addProduct(ProductDto.WriteP productDto);

	// 상품수정
	@Update("UPDATE product SET product_name = #{product.productName}, " +
	        "product_notice = #{product.productNotice}, " +
	        "product_ntock = #{product.productStock}, " +
	        "product_price = #{product.productPrice}, " +
	        "prodcut_size = #{prodcut.prodcutSize}, category = #{product.category} WHERE pno = #{pno}")
	public Long updateProduct(ProductDto.WriteP UpProductWrite);
    
	// 상품번호로 조회
    @Select("SELECT * FROM product WHERE pno = #{pno}")
    public Product findByProduct(Long pno);
	
    // 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
    @Select("SELECT * FROM product_photo WHERE pno = #{pno}")
    public List<ProductPhoto> getProductPhotos(Long pno);
   
    @Delete("DELETE FROM product WHERE pno = #{pno}")
    public Integer deleteProduct(Long pno);

    @Delete("DELETE FROM product_photo WHERE pno = #{pno}")
    public boolean deleteProductPhotos(Long pno);
    
}
