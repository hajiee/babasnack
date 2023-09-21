package com.babasnack.demo.product.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.ProductPhoto;

public interface ProductPhotoDao {
	// 사진 저장
	@Insert("INSERT INTO product_photo (pno, productImgNo, productImg, productSaveImg) "
			+ "VALUES (#{pno}, #{productImgNo}, #{productImg}, #{productSaveImg})")
	public Integer saveProductPhoto(ProductPhoto photo);

	// 사진 수정
	@Update("UPDATE product_photo SET product_img = #{productImg} WHERE product_imgno = #{productImgNo}")
	public void updateProductPhoto(Long photoImgNo, String productImg);

	// 사진 삭제
	@Delete("DELETE FROM product_photo WHERE product_imgno = #{productImgNo}")
	public void deleteProductPhoto(Long photoImgNo);
}
