<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ��</title>
</head>
<body>
	<!-- ��ǰ����Ʈ -->
	<div class="prdlist_default">
		<c:if test="${empty productPage.products}">
			<div class="product-item empty">
				<!-- ������� �� ������ ���� -->
			</div>
		</c:if>
		<c:forEach items="${productPage.products}" var="p">
				<div class="product-item">
					<!-- ��ǰ ��� �׸� ���� -->
					<div class="product-image">
						<img src="${p.imageUrls}" alt="${p.productName}">
					</div>
					<div class="product-details">
						<h2>${p.productName}</h2>
						<p class="product-description">${p.productDescription}</p>
						<div class="product-price">
							<span class="price-label">����:</span> <span class="price-value">${p.productPrice}��</span>
						</div>
						<div class="product-stock">
							<span class="stock-label">���:</span> <span class="stock-value">${p.productStock}��
								����</span>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
</body>
</html>