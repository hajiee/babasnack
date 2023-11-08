package com.babasnack.demo.product.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.entity.Review;
import com.babasnack.demo.product.dao.ProductAdminDao;
import com.babasnack.demo.product.dao.ProductDao;
import com.babasnack.demo.product.dao.ProductPhotoDao;
import com.babasnack.demo.product.dao.ReviewDao;
import com.babasnack.demo.product.dto.ProductDto;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductAdminDao productAdminDao;
    @Autowired
    private ProductPhotoDao productPhotoDao;
    @Autowired
    private ReviewDao reviewDao;

    @Value("${defaultproductSaveImg}")
    private String defaultProductSaveImg;
    @Value("${productSaveImg}")
    private String productSaveImg;
    @Value("${productImgUrl}")
    private String productImgUrl;

    @Value("${numberOfProductsPerPage}")
    private int numberOfProductsPerPage;
    @Value("${sizeOfPagination}")
    private int sizeOfPagination;
    
    @Value("${numberOfBoardesPerPage}")
    private int numberOfBoardesPerPage;
    @Value("${sizeOfBoardPagination}")
    private int sizeOfBoardPagination;

    // 상품 목록(카테고리) 조회 서비스 메서드
    public List<Product> getProductListByCategory(String category) {
        if (category != null && ("DOG".equals(category) || "CAT".equals(category))) {
            return productDao.findByCategory(category);
        } else {
            return productDao.findAllProducts();
        }
    }

    // 상품 목록(키워드) 조회 서비스 메서드
    public List<Product> getProductListByKeyword(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return productDao.findByKeyword(keyword);
        } else {
            return productDao.findAllProducts();
        }
    }

    // 특정 상품 조회 서비스 메서드 - 만약 해당하는 상품이 없다면 null이 반환
    public Product getProductByProductName(String productName) {
        return productDao.findByProductName(productName);
    }

    // 특정 상품 번호조회 서비스 메서드 - 만약 해당하는 상품이 없다면 null이 반환
    public Product getProductByPno(Long pno) {
        return productDao.findByProduct(pno);
    }

    // 한 페이지당 상품 수 조회 서비스 메서드
    public List<Product> getPageOne(Long startRownum, Long endRownum) {
        return productDao.findProductsByPage(startRownum, endRownum);
    }

    // 상품, 이미지들, 리뷰들, 리뷰 개수, 리뷰 평점 평균을 읽어 출력
    // 특정 상품의 상세 정보 조회 서비스 메서드
    public ProductDto.ReadP getProductDetail(Long pno) {
    	 Product p = productDao.findByProduct(pno);
    	    if (p == null)
    	        return null;

    	    List<ProductPhoto> images = productAdminDao.findProductPhotos(pno);
    	    if (images.isEmpty()) {
    	        // 사진이 없다면 default.jpg를 출력
    	        ProductPhoto defaultPhoto = new ProductPhoto();
    	        defaultPhoto.setProductSaveImg("default.jpg");
    	        images = Arrays.asList(defaultPhoto);
    	    } else {
    	        // 실제 이미지 URL로 변환하여 저장
    	        for (ProductPhoto photo : images) {
    	            String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
    	            photo.setProductSaveImg(imageUrl);
    	        }
    	    }

    	    List<Review> reviews = reviewDao.findByPno(pno);
    	    Long countOfReview = reviewDao.countByPno(pno);
    	    Double avgOfReview = reviewDao.avgByPno(pno);

    	    return new ProductDto.ReadP(p.getPno(), p.getProductName(), p.getProductNotice(), p.getProductStock(),
    	            p.getProductPrice(), p.getProductSize(), countOfReview, avgOfReview, images, reviews);
    	}
}