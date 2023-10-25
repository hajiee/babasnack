<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/product-css/product.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>상품상세정보</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '<c:out value="${msg}" />';
	if (msg !== '')
		alert(msg);
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				// 재고 표시
				var products = $
				{
					productPage.products
				}
				;
				for (var i = 0; i < products.length; i++) {
					var product = products[i];
					var stockMessage;
					if (product.productStock >= 0) {
						stockMessage = product.productStock + "개 남음";
					} else {
						stockMessage = "재고 없음";
					}
					$(".prdlist_default .price_box:eq(" + i + ")").append(
							"<span>" + stockMessage + "</span>");
				}
			});
</script>
</head>
<body>
	<div id="page">
		<header>
			<!-- 공지, 로고 -->
			<jsp:include page="/WEB-INF/views/include/header.jsp" />
		</header>
		<nav>
			<!-- 메뉴 -->
			<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		</nav>
		<main>
			<aside>
			</aside>
			<section>
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
			</section>
			<aside>
			</aside>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>
