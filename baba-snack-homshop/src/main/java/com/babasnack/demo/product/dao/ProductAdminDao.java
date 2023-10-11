package com.babasnack.demo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.dto.ProductDto;

@Mapper
public interface ProductAdminDao {
	// 상품 등록
    @Insert("INSERT INTO product (pno, product_name, product_notice, product_stock, product_price, product_size, reserve, product_cnt, category, product_day) "
            + "VALUES (product_seq.nextval, #{productName}, #{productNotice}, #{productStock}, #{productPrice}, #{productSize}, #{reserve}, #{productCnt}, #{category.name()}, #{productDay})")
    public Long addProduct(ProductDto.WriteP writeP);

    // 상품 수정
    @Update("UPDATE product SET " +
            "product_name = #{productName}," +
            "product_notice = #{productNotice}," +
            "product_stock = #{productStock}," +
            "prodcut_price = #{prodcutPrice}," + 
            "prodcut_size =#{prodcutSize}," + 
            "category =#{category} WHERE pno=#{pno}")
    public Long updateProduct(ProductDto.WriteP UpProductWrite);

    // 상품번호로 조회
    @Select("SELECT * FROM product WHERE pno=#{pno}")
    public Product findByProduct(Long pno);

	// 주어진 상품 번호(pno)에 해당하는 모든 사진들을 조회
	@Select("SELECT * FROM PRODUCT_PHOTO WHERE PNO=#{pNo}")
	public List<String> findProductPhotos(Long pNo);

	// 상품 삭제
	@Delete("DELETE FROM PRODUCT WHERE PNO=#{pNo}")
	public Integer deleteProduct(Long pNo);

	// 상품에 연관된 사진 삭제
	@Delete("DELETE FROM PRODUCT_PHOTO WHERE PNO=#{pNo}")
	public boolean deleteAllPhotosByPNo(Long pNo);
}
