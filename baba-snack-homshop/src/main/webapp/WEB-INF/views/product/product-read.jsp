<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품상세</title>
</head>
<body>
	<!-- 상품리스트 -->
	<div class="prdlist_default">
		<c:if test="${empty productPage.products}">
			<div class="product-item empty">
				<!-- 비어있을 때 보여줄 내용 -->
			</div>
		</c:if>
		<c:forEach items="${productPage.products}" var="p">
				<div class="product-item">
					<!-- 상품 목록 항목 내용 -->
					<div class="product-image">
						<img src="${p.imageUrls}" alt="${p.productName}">
					</div>
					<div class="product-details">
						<h2>${p.productName}</h2>
						<p class="product-description">${p.productDescription}</p>
						<div class="product-price">
							<span class="price-label">가격:</span> <span class="price-value">${p.productPrice}원</span>
						</div>
						<div class="product-stock">
							<span class="stock-label">재고:</span> <span class="stock-value">${p.productStock}개
								남음</span>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
</body>
</html>