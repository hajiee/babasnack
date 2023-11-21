<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/product-css/product.css">
<link rel="stylesheet" href="/css/product-css/product-list.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>상품목록</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '${msg}';
	if (msg !== '')
		alert(msg);
	
	const v = '${products}';
	console.log(v);
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
		<main id="product-main">
			<aside id="best">
				<!-- 베스트상품 -->
				<jsp:include page="/WEB-INF/views/include/aside-best.jsp" />
			</aside>
			<section>
				<!-- 검색 결과상품 -->
				<h3 class="blind">검색결과</h3>
				
				<!-- 상품추출 -->
				<div class="mproduct_area">
					<ul class="product-grid">
						<c:forEach items="${products}" var="product">
							<li>
								<div class="product">
									<div class="product-image">
										<a href="/product/product-read?pno=${product.pno}">
											<img class="get-product-img" src="c:/upload/productImg/${product.productPhoto[0].productSaveImg}" alt="${product.productName}">
										</a>
									</div>
									<div class="product-info">
										<p class="product-name">${product.productName}(${product.productSize}g)</p>
										<p class="product-price">${product.productPrice}원</p>
										<p class="product-Stock">재고:${product.productStock}</p>
										<p class="product-Category"><a href="/product/?category=${product.category}">${product.category}</a></p>
									</div>
								</div>
							</li>
						</c:forEach>
						<c:if test="${empty products}">
							<p id="not-resert">일치하는 상품이 없습니다.</p>
						</c:if>
					</ul>
				</div>
			</section>
			<div>
				<aside>
				</aside>
			</div>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>
