package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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

	    // 상품번호로 조회
	    @Select("SELECT * FROM product WHERE pno=#{pno}")
	    public Product findByProduct(Long pno);

		// 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
		@Select("SELECT * FROM PRODUCT_PHOTO WHERE PNO=#{pno}")
		public List<ProductPhoto> findProductPhotos(Long pno);

		// 상품 삭제
		@Delete("DELETE FROM PRODUCT WHERE PNO=#{pno}")
		public Integer deleteProduct(Long pno);

		// 상품에 연관된 사진 삭제
		@Delete("DELETE FROM PRODUCT_PHOTO WHERE PNO=#{pno}")
		public boolean deleteAllPhotosByPNo(Long pno);
}