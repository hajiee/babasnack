<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/product-css/product.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK-cat</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '${msg}';
	if (msg !== '')
		alert(msg);
</script>
<script>
$(document).ready(function() {
    // 각 상품의 이미지 URL을 설정하는 함수
    function setProductImage() {
        $('.get-product-img img').each(function() {
            var imgElement = $(this);
            var imgSrc = imgElement.data('img-src');
            imgElement.attr('src', imgSrc);
        });
    }

    // 페이지 로드 시 상품 이미지 설정
    setProductImage();
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
			<aside id="best">
				<!-- 베스트상품 -->
				<jsp:include page="/WEB-INF/views/include/aside-best.jsp" />
			</aside>
			<section>
				<!-- 메인 상품 -->
				<h3 class="blind">고양이 간식상품</h3>
				<div class="total-sort">
					<p class="total" style="color: #a2a2a2;">
						In this category are <strong>CAT</strong> products.
					</p>
				</div>
				<!-- 상품리스트 -->
				<div class="productList_default">
					<!-- 상품추출 -->
					
					<c:forEach items="${products}" var="product">
    					<!-- 상품 정보 출력 -->
    					${product.productName}
    					${product.productPrice}
   					<!-- 추가적인 출력 내용 작성 -->
					</c:forEach>

					<div class="mproduct_area">
						<ul class="product-grid">
							<c:forEach items="${products}" var="product">
								<li>
									<div class="product">
										<div class="product-image">
											<a href="/product/product-read?pno=${product.pno}">
												<img class="get-product-img" data-img-src="${product.photos[0].productSaveImg}" alt="${product.productName}">
											</a>
										</div>
										<div class="product-info">
											<p class="product-name">${product.productName}</p>
											<p class="product-price">${product.productPrice}원</p>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div id="pagination" style="display: flex; justify-content: center;">
					<ul class="pagination">
						<c:if test="${productPage.prev > 0}">
							<li class="page-item">
								<a class="page-link" href="/product/product-cat?pageno=${productPage.prev}">이전으로</a>
							</li>
						</c:if>
						<c:forEach begin="${productPage.start}" end="${productPage.end}" var="i">
							<c:if test="${i == productPage.pageno}">
								<li class="page-item active">
									<a class="page-link" href="/product/product-cat?pageno=${i}">${i}</a>
								</li>
							</c:if>
							<c:if test="${i != productPage.pageno}">
								<li class="page-item">
									<a class="page-link" href="/product/product-cat?pageno=${i}">${i}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${productPage.next > 0}">
							<li class="page-item">
								<a class="page-link" href="/product/product-cat?pageno=${productPage.next}">다음으로</a>
							</li>
						</c:if>
					</ul>
				</div>
			</section>
			<div id="ad">
				<aside>
					<!-- 광고 -->
					<jsp:include page="/WEB-INF/views/include/aside.jsp" />
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