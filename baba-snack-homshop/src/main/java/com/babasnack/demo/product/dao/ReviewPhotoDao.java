package com.babasnack.demo.product.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.babasnack.demo.entity.ReviewPhoto;

@Mapper
public interface ReviewPhotoDao {
	@Insert("insert into Review_photo()")
	public Integer saveReviewPhoto(ReviewPhoto reviewPhoto);
}
