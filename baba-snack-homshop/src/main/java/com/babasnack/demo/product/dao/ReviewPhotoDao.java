package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.babasnack.demo.entity.ReviewPhoto;

@Mapper
public interface ReviewPhotoDao {
	@Insert("insert into Review_photo(rno, review_img, review_saveimg) values (#{rno}, #{reviewImg}, #{reviewSaveImg})")
	@SelectKey(statement = "SELECT review_photo_seq.nextval FROM dual", keyProperty = "reviewImgNo", before = true, resultType = Long.class)
	public Integer saveReviewPhoto(ReviewPhoto reviewPhoto);

	@Select("SELECT * FROM review_photo WHERE rno = #{rno}")
	public List<ReviewPhoto> findReviewPhotosByRno(Long rno);
}
