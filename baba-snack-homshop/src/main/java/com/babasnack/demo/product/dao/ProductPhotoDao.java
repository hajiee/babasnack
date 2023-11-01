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
    // 상품 사진 저장
	@Insert("INSERT INTO PRODUCT_PHOTO (PRODUCT_IMGNO, PNO, PRODUCT_IMG, PRODUCT_SAVEIMG) " +
	        "VALUES (product_photo_seq.nextval, #{pno}, #{productImg}, #{productSaveImg})")
	public void saveProductPhoto(ProductPhoto photo);

    // 사진 수정 by ImgNo and PNo (복합 키)
    @Update("UPDATE PRODUCT_PHOTO SET PRODUCT_IMG = #{productImg} WHERE PRODUCT_IMGNO = #{productImgNo} AND PNO = #{pno}")
    public void updateProductPhoto(Long productImgNo, Long pno, String productImg);

    // 사진 삭제
    @Delete("DELETE FROM PRODUCT_PHOTO WHERE PRODUCT_IMGNO = #{productImgNo}")
    public Long deleteProductPhoto(Long photoImgNo);

    // 사진 삭제 by ImgNo and PNo (복합 키)
    @Delete("DELETE FROM PRODUCT_PHOTO WHERE PRODUCT_IMGNO = #{productImgNo} AND PNO = #{pno}")
    public Long deleteProductPhotoByImageAndPNo(Long productImgNo, Long pno);

    // 상품사진 목록
    @Select("SELECT * FROM PRODUCT_PHOTO")
    public List<ProductPhoto> findByPno(Long pno);

    // 상품 번호에 해당하는 사진들을 조회
    @Select("SELECT * FROM PRODUCT_PHOTO WHERE PNO = #{pno}")
    public List<ProductPhoto> getProductPhotosByPNo(Long pno);
}
