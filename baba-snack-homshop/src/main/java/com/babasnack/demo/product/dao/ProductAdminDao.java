package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dto.ProductDto;

@Mapper
public interface ProductAdminDao {
	// 상품 등록
	public Long addProduct(ProductDto.WriteP writeP);

    // 상품 수정
    public Long updateProduct(ProductDto.WriteP UpProductWrite);

    // 상품번호로 조회
    public Product findByProduct(Long pno);

	// 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
	public List<String> findProductPhotos(Long pno);

	// 상품 삭제
	public Integer deleteProduct(Long pno);

	// 상품에 연관된 사진 삭제
	public boolean deleteAllPhotosByPNo(Long pno);
}
