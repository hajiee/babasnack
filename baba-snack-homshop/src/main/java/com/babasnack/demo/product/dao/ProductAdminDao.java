package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.Provider.ProductAdminSqlProvider;
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
	@Update("UPDATE product SET productName = #{product.productName}, " +
	        "productNotice = #{product.productNotice}, " +
	        "productStock = #{product.productStock}, " +
	        "productPrice = #{product.productPrice}, " +
	        "prodcutSize = #{prodcut.prodcutSize}, category = #{product.category} WHERE pno = #{pno}")
	public Long updateProduct(ProductDto.WriteP prodcutDto);
    
    // 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
    @Select("SELECT * FROM product_photo WHERE pno = #{pno}")
    public List<ProductPhoto> getProductPhotos(Long pno);

    // Provider 클래스의 메서드를 사용하여 업데이트 쿼리를 실행할 때 사용
    @UpdateProvider(type = ProductAdminSqlProvider.class, method = "updateProductWithPhotos")
    public void updateProductWithPhotos(ProductDto.WriteP productDto, List<ProductPhoto> photoList);
    
    @Delete("DELETE FROM product WHERE pno = #{pno}")
    public Integer deleteProduct(Long pno);

    @Delete("DELETE FROM product_photo WHERE pno = #{pno}")
    public boolean deleteProductPhotos(Long pno);
    
	// 사진 저장
    public Integer saveProductPhoto(ProductPhoto photo);
    
    default void saveProductPhotos(List<ProductPhoto> photos) {
        for (ProductPhoto photo : photos) {
            saveProductPhoto(photo);
        }
    }
}
