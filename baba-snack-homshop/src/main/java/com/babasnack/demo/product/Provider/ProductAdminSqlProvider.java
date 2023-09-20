package com.babasnack.demo.product.Provider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.babasnack.demo.entity.ProductPhoto;

public class ProductAdminSqlProvider {
	public String updateProductWithPhotos(Map<String, Object> parameter) {
	      StringBuilder sqlBuilder = new StringBuilder();
	      sqlBuilder.append("UPDATE product SET productName = #{product.productName}, ");
	      sqlBuilder.append("productNotice = #{product.productNotice}, ");
	      sqlBuilder.append("productStock = #{product.productStock}, ");
	      sqlBuilder.append("productPrice = #{product.productPrice}, ");
	      sqlBuilder.append("prodcutSize = #{prodcut.prodcutSize} WHERE pno = #{product.pno};");

	      @SuppressWarnings("unchecked")
	      List<ProductPhoto> photoList =
	          (List<ProductPhoto>) parameter.getOrDefault("photos", Collections.emptyList());
	      
	      if (!photoList.isEmpty()) {
	          for (int i=0; i<photoList.size(); i++) {
	              String paramNamePrefix = "photos[" + i + "]";
	              
	              sqlBuilder.append(
	                  "INSERT INTO product_photo (pno, productImgNo, productImg, productSaveImg) ")
	                  .append(String.format(
	                      "VALUES (#{%s.pno,jdbcType=BIGINT}, #{%s.imgNo,jdbcType=BIGINT}, #{%s.img,jdbcType=VARCHAR}, #{%s.saveImg,jdbcType=VARCHAR})",
	                      paramNamePrefix,
	                      paramNamePrefix,
	                      paramNamePrefix,
	                      paramNamePrefix))
	                  .append(";");
	          }
	      }
	      
	      return sqlBuilder.toString();
	  }
}
