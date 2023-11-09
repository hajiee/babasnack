package com.babasnack.demo;

import com.babasnack.demo.entity.Review;
import com.babasnack.demo.product.dao.ReviewDao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewDaoTest {
    @Autowired
    private ReviewDao reviewDao;

    //@Test
    public void testSaveReview() {
        // 테스트용 리뷰 객체 생성
        Review review = new Review();
        review.setReviewDate(LocalDate.now()); // 리뷰 작성 날짜 설정
        review.setReviewNotice("테스트 리뷰 내용5"); // 리뷰 내용 설정
        review.setStar((double) 5); // 별점 설정
        review.setPno(83L); // 상품 번호 설정
        review.setReviewWrite("테스트 작성자5"); // 리뷰 작성자 설정

        // 리뷰 저장
        reviewDao.save(review);

        // 저장된 리뷰 확인을 위한 출력
        System.out.println(review.getRno());
    }
    
   // @Test
    public void aaa() {
    	System.out.println(reviewDao.findReviewPhotosByRno(4L));
    }
    
    
    
}

