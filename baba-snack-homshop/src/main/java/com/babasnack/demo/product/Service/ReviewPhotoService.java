package com.babasnack.demo.product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.ReviewPhoto;
import com.babasnack.demo.product.dao.ReviewDao;
import com.babasnack.demo.product.dao.ReviewPhotoDao;

@Service
public class ReviewPhotoService {
	@Autowired
	private ReviewPhotoDao reviewPhotoDao;
	@Autowired
	private ReviewDao reviewDao;

	@Transactional(rollbackFor = Exception.class)
	public void saveReviewPhoto(ReviewPhoto reviewPhoto) {
		// 리뷰 사진 저장 로직
		reviewPhotoDao.saveReviewPhoto(reviewPhoto);
	}

	public List<ReviewPhoto> findReviewPhotosByRno(Long rno) {
		// 특정 리뷰에 연관된 사진들 조회 로직
		return reviewDao.findReviewPhotosByRno(rno);
	}

}
