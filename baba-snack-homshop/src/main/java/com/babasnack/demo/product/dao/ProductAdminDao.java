package com.babasnack.demo.product.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;

@Mapper
public interface ProductAdminDao {
		// 상품 등록
		@Insert("INSERT INTO product (pno, product_name, product_notice, product_stock, product_price, product_size, category, reserve, product_day) "
	            + "VALUES (#{pno}, #{productName}, #{productNotice}, #{productStock}, #{productPrice}, #{productSize}, #{category, jdbcType=VARCHAR}, #{reserve, jdbcType=NUMERIC}, #{productDay, jdbcType=DATE})")
		@SelectKey(statement="select product_seq.nextval from dual", before = true, keyProperty = "pno", resultType = Long.class)
		public Long addProduct(Product product);

	    // 상품 등록 시 사진 정보도 함께 등록
	    @Insert("INSERT INTO PRODUCT_PHOTO (PRODUCT_IMGNO, PNO, PRODUCT_IMG, PRODUCT_SAVEIMG) " +
	            "VALUES (product_photo_seq.nextval, #{pno}, #{productImg}, #{productSaveImg})")
	    public void addProductPhoto(ProductPhoto photo);

	    // 상품 수정
		@Update("UPDATE product SET " +
		        "product_name = #{productName}," +
		        "product_notice = #{productNotice}," +
		        "product_stock = #{productStock}," +
		        "product_price = #{productPrice}," + 
		        "product_size = #{productSize}," + 
		        "category = #{category, jdbcType=VARCHAR} WHERE pno = #{pno}")
		public Long updateProduct(Product product);

		// 상품 삭제
		@Delete("DELETE FROM PRODUCT WHERE PNO=#{pno}")
		public Integer deleteProduct(Long pno);

		// 상품에 연관된 사진 삭제
		@Delete("DELETE FROM PRODUCT_PHOTO WHERE PNO=#{pno}")
		public Long deleteAllPhotosByPNo(Long pno);
		
		// 상품을 삭제할 때 상품의 리뷰들을 모두 삭제
		@Delete("delete from review where pno=#{pno}")
		public Integer deleteAllReviewByPNo(Long pno);
		
		@Delete("DELETE FROM review_photo WHERE rno IN (SELECT rno FROM review WHERE pno = #{pno})")
		public Integer deleteAllReviewPhoto(Long pno);
}