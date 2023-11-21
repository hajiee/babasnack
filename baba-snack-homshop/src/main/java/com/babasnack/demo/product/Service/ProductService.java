package com.babasnack.demo.product.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.babasnack.demo.product.dto.ProductDto.ListP;

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

    // 카테고리별 별점 평균 4 이상인 상품 목록
    // 상품 목록(카테고리) 조회 서비스 메서드
    public List<ProductDto.ListP> getProductListByCategory(String category) {
        if (category != null && ("DOG".equals(category) || "CAT".equals(category))) {
            List<Product> products = productDao.findByCategory(category);
            List<ProductDto.ListP> productList = new ArrayList<>();

            for (Product product : products) {
                List<ProductPhoto> productPhoto = productPhotoDao.getProductPhotosByPNo(product.getPno());
                if (productPhoto == null) {
                    productPhoto = Collections.emptyList(); // 이미지가 없는 경우 빈 리스트 할당
                } else {
                    // 실제 이미지 URL로 변환하여 저장
                    for (ProductPhoto photo : productPhoto) {
                        String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
                        photo.setProductSaveImg(imageUrl);
                    }
                }

                productList.add(new ProductDto.ListP(
                    product.getPno(),
                    product.getProductName(),
                    product.getProductStock(),
                    product.getProductPrice(),
                    product.getProductSize(),
                    product.getCategory(),
                    productPhoto // 이미지 목록 할당
                ));
            }
            return productList;
        } else {
            // 기본적으로 모든 상품 목록을 반환
            List<Product> products = productDao.findBestProducts();
            List<ProductDto.ListP> productList = new ArrayList<>();

            for (Product product : products) {
                List<ProductPhoto> productPhoto = productPhotoDao.getProductPhotosByPNo(product.getPno());
                if (productPhoto == null) {
                    productPhoto = Collections.emptyList(); // 이미지가 없는 경우 빈 리스트 할당
                } else {
                    // 실제 이미지 URL로 변환하여 저장
                    for (ProductPhoto photo : productPhoto) {
                        String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
                        photo.setProductSaveImg(imageUrl);
                    }
                }

                productList.add(new ProductDto.ListP(
                    product.getPno(),
                    product.getProductName(),
                    product.getProductStock(),
                    product.getProductPrice(),
                    product.getProductSize(),
                    product.getCategory(),
                    productPhoto // 이미지 목록 할당
                ));
            }

            return productList;
        }
    }

    // 키워드로 별점 평균 4 이상인 상품 목록
    // 상품 목록(키워드) 조회 서비스 메서드
    public List<ProductDto.ListP> getProductListByKeyword(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Product> products = productDao.findByKeyword(keyword);
            List<ProductDto.ListP> productList = new ArrayList<>();

            for (Product product : products) {
                List<ProductPhoto> productPhoto = productPhotoDao.getProductPhotosByPNo(product.getPno());
                if (productPhoto == null) {
                    productPhoto = Collections.emptyList(); // 이미지가 없는 경우 빈 리스트 할당
                } else {
                    // 실제 이미지 URL로 변환하여 저장
                    for (ProductPhoto photo : productPhoto) {
                        String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
                        photo.setProductSaveImg(imageUrl);
                    }
                }

                productList.add(new ProductDto.ListP(
                    product.getPno(),
                    product.getProductName(),
                    product.getProductStock(),
                    product.getProductPrice(),
                    product.getProductSize(),
                    product.getCategory(),
                    productPhoto // 이미지 목록 할당
                ));
            }

            return productList;
        } else {
            // 기본적으로 모든 상품 목록을 반환
            List<Product> products = productDao.findBestProducts();
            List<ProductDto.ListP> productList = new ArrayList<>();

            for (Product product : products) {
                List<ProductPhoto> productPhoto = productPhotoDao.getProductPhotosByPNo(product.getPno());
                if (productPhoto == null) {
                    productPhoto = Collections.emptyList(); // 이미지가 없는 경우 빈 리스트 할당
                } else {
                    // 실제 이미지 URL로 변환하여 저장
                    for (ProductPhoto photo : productPhoto) {
                        String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
                        photo.setProductSaveImg(imageUrl);
                    }
                }

                productList.add(new ProductDto.ListP(
                    product.getPno(),
                    product.getProductName(),
                    product.getProductStock(),
                    product.getProductPrice(),
                    product.getProductSize(),
                    product.getCategory(),
                    productPhoto // 이미지 목록 할당
                ));
            }

            return productList;
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

    	    List<ProductPhoto> images = productPhotoDao.getProductPhotosByPNo(pno);
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

    	    List<Review> reviews = reviewDao.findByPnoWithPhotos(pno);
    	    Long countOfReview = reviewDao.countByReview(pno);
    	    Double avgOfReview = reviewDao.avgByPnoStar(pno);

    	    return new ProductDto.ReadP(p.getPno(), p.getProductName(), p.getProductNotice(), p.getProductStock(),
    	            p.getProductPrice(), p.getProductSize(), countOfReview, avgOfReview, images, reviews);
    	}
    
    // 별점 평균 4 이상인 최신 상품 목록 가져오기
    public List<ProductDto.ListP> getRecentBestProducts() {
        List<Product> products = productDao.findBestProducts(); // 별점 평균 4 이상인 최신 상품 목록 조회
        List<ProductDto.ListP> productList = new ArrayList<>();

        for (Product product : products) {
            List<ProductPhoto> productPhoto = productPhotoDao.getProductPhotosByPNo(product.getPno());
            if (productPhoto == null) {
                productPhoto = Collections.emptyList(); // 이미지가 없는 경우 빈 리스트 할당
            } else {
                // 실제 이미지 URL로 변환하여 저장
                for (ProductPhoto photo : productPhoto) {
                    String imageUrl = productImgUrl + photo.getProductSaveImg(); // 이미지 URL 생성
                    photo.setProductSaveImg(imageUrl);
                }
            }

            productList.add(new ProductDto.ListP(
                product.getPno(),
                product.getProductName(),
                product.getProductStock(),
                product.getProductPrice(),
                product.getProductSize(),
                product.getCategory(),
                productPhoto // 이미지 목록 할당
            ));
        }

        return productList;
    }
}