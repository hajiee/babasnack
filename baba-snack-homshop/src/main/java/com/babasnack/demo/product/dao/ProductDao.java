package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.babasnack.demo.entity.Product;

@Repository
@Mapper
public interface ProductDao {
    // 상품목록
	@Select("SELECT * FROM product right outer join product_photo on product.pno = product_photo.pno")
	public List<Product> findAllProducts();

    // 상품번호로 조회
	@Select("SELECT * FROM product RIGHT OUTER JOIN product_photo ON product.pno = product_photo.pno WHERE product.pno = #{pno}")
	public Product findByProduct(Long pno);

    // 상품조회
    @Select("SELECT * FROM product right outer join product_photo on product.pno = product_photo.pno WHERE product_name = #{productName}")
    public Product findByProductName(String productName);
    
    // 카테고리별 상품 목록 조회
    @Select("SELECT * FROM product right outer join product_photo on product.pno = product_photo.pno WHERE category = #{category}")
    public List<Product> findByCategory(String category);

    // 상품명으로 부분 일치 검색
    @Select("SELECT * FROM product right outer join product_photo on product.pno = product_photo.pno WHERE product_name LIKE '%' || #{keyword} || '%'")
    public List<Product> findByKeyword(String keyword);
    
    // 한 페이지당 상품 수
    @Select("SELECT * FROM (SELECT rownum as rnum,p.* FROM (SELECT /*+ index_desc(product_pk_pno)*/ * from 	product)p where rownum <=#{endRownum}) where rnum >=#{startRownum}") 
    public List<Product> findProductsByPage(@Param("startRownum") Long startRownum,@Param("endRownum") Long endRownum);
    
    @Select("select count(*) from product")
    public Long count();
    
    // 최신등록순 상품목록
    @Select("SELECT * FROM product right outer join product_photo on product.pno = product_photo.pno ORDER BY product.productDay DESC")
    public List<Product> findAllProductsOrderByRegistrationDate();
      
    // 베스트 상품목록
    @Select("SELECT * FROM ("
    		+ "    SELECT p.*, AVG(r.star) AS avg_star"
    		+ "    FROM product p"
    		+ "    LEFT JOIN review r ON p.pno = r.pno"
    		+ "    GROUP BY p.pno\r\n"
    		+ "    HAVING AVG(r.star) >= 4"
    		+ "    ORDER BY p.productDay DESC"
    		+ ")"
    		+ "WHERE ROWNUM <= 4")
    public List<Product> findBestProducts();
}
