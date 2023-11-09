package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.entity.ReviewPhoto;

@Mapper
public interface ReviewDao {
	@Insert("INSERT INTO Review (rno, review_date, review_notice, star, pno, review_write) "
	        + "VALUES (#{rno}, #{reviewDate}, #{reviewNotice}, #{star}, #{pno}, #{reviewWrite})")
	@SelectKey(statement = "SELECT review_seq.nextval FROM dual", keyProperty = "rno", before = true, resultType = Long.class)
	public void save(Review review);

	// 특정 상품의 리뷰들을 출력
	@Select("SELECT r.*, p.* FROM review r INNER JOIN review_photo p ON r.rno = p.rno WHERE r.pno = #{pno} ORDER BY r.rno DESC")
	public List<Review> findByPnoWithPhotos(Long pno);

	// 상품을 삭제할 때 상품의 리뷰들을 모두 삭제
	@Delete("delete from review where pno=#{pno}")
	public Integer deleteByPno(Long pno);

	// 상품 리뷰들의 개수
	@Select("select count(*) from review where pno=#{pno}")
	public Long countByPno(Long pno);

	// 상품 리뷰들의 별점 평균
	@Select("select round(avg(star),2) from review where pno=#{pno}")
	public Double avgByPno(Long pno);

	// 특정리뷰에 연관된 사진들
	@Select("SELECT * FROM review_photo WHERE rno = #{rno}")
	public List<ReviewPhoto> findReviewPhotosByRno(Long rno);

}
