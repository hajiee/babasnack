package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dto.ProductDto;

@Mapper
public interface ProductAdminDao {
	// 상품등록
	@Insert("INSERT INTO your_table_name (pno, product_name, product_notice, product_stock, product_price, product_size, reserve, product_cnt, category, product_day) " +
            "VALUES (product_seq.nextval, #{productName}, #{productNotice}, #{productStock}, #{productPrice}, #{productSize}, #{reserve}, #{productCnt}, #{category.name()}, #{productDay})")
    public Long addProduct(ProductDto.WriteP writeP);

	// 상품수정
	@Update("UPDATE product SET product_name = #{productName}, " +
	        "product_notice = #{productNotice}, " +
	        "product_ntock = #{productStock}, " +
	        "product_price = #{productPrice}, " +
	        "prodcut_size = #{prodcutSize}, category = #{category} WHERE pno = #{pno}")
	public Long updateProduct(ProductDto.WriteP UpProductWrite);
    
	// 상품번호로 조회
    @Select("SELECT * FROM product WHERE pno = #{pno}")
    public Product findByProduct(Long pno);
	
    // 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
    @Select("SELECT * FROM product_photo WHERE pno = #{pno}")
    public List<String> findProductPhotos(Long pno);
   
    @Delete("DELETE FROM product WHERE pno = #{pno}")
    public Integer deleteProduct(Long pno);

    @Delete("DELETE FROM product_photo WHERE pno = #{pno}")
    public boolean deleteProductPhotos(Long pno);
    
}
