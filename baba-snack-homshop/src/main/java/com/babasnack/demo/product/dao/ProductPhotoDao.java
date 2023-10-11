package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.ProductPhoto;

@Mapper
public interface ProductPhotoDao {
	// 사진 저장
	@Insert("INSERT INTO product_photo (product_imgno, pno, product_img, product_saveimg)" +
            "VALUES (product_photo_seq.nextval, #{pno}, #{productImg}, #{productSaveImg})")
	public Integer saveProductPhoto(ProductPhoto photo);

	// 사진 수정
	@Update("UPDATE product_photo SET product_img = #{productImg} WHERE product_imgno = #{productImgNo}")
	public void updateProductPhoto(Long photoImgNo, String productImg);

	// 사진 삭제
	@Delete("DELETE FROM product_photo WHERE product_imgno = #{productImgNo}")
	public Long deleteProductPhoto(Long photoImgNo);

	@Delete("DELETE FROM product_photo WHERE product_saveimg = #{photoPath}")
	public int deleteProductPhotoByPath(String photoPath);
	
	// 상품목록
	@Select("SELECT * FROM product_photo")
	public List<ProductPhoto> findByPno(Long pno);

	// 상품 번호에 해당하는 사진들을 조회
	@Select("SELECT * FROM product_photo WHERE pno = #{pno}")
	public List<ProductPhoto> getProductPhotosByPNo(Long pno);


}
