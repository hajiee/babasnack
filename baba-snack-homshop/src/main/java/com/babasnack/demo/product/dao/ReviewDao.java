package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.babasnack.demo.entity.Review;

public interface ReviewDao {
	@Insert("insert into review values(review_seq.nextval, #{content}, #{star}, #{pno})")
	public void save(Review review);

	// 특정 상품의 리뷰들을 출력
	@Select("select * from review where pno=#{pno} order by rno desc")
	public List<Review> findByPno(Long pno);

	// 상품을 삭제할 때 상품의 리뷰들을 모두 삭제
	@Delete("delete from review where pno=#{pno}")
	public Integer deleteByPno(Long pno);

	// 상품 리뷰들의 개수
	@Select("select count(*) from review where pno=#{pno}")
	public Long countByPno(Long pno);

	// 상품 리뷰들의 별점 평균
	@Select("select round(avg(star),2) from review where pno=#{pno}")
	public Double avgByPno(Long pno);
}
