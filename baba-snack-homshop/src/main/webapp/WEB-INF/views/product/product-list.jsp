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
</script>
<script>

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
				<!-- 검색 결과상품 -->
				<h3 class="blind">검색결과</h3>
				<!-- 상품추출 -->
				<div class="mproduct_area">
					<ul>
						<c:forEach items="${productPage.products}" var="p">
							<li><a href="/product/product-read/${p.pno}" class="box">
									<div class="img">
										<img class="MS_prod_img_m" src="${p.imageUrls}" alt="">
									</div>
									<dl>
										<dt>${p.productName}</dt>
										<dd class="txt">${p.productSize}</dd>
										<dd class="price_box">
											<p>
												<span class="price">${p.productPrice}</span>
											</p>
										</dd>
									</dl>
							</a></li>
						</c:forEach>

						<!-- 일치하는 상품이 없을 경우 -->
						<c:if test="${empty productPage.products}">
							<li style="padding-left:500px; padding-bottom:50px;">일치하는 상품이 없습니다.</li>
						</c:if>
					</ul>
				</div>
				<div id=pagination style="display: flex; justify-content: center;">
					<ul class="pagination">
						<c:if test="${page.prev>0}">
							<li class="page-item"><a class="page-link"
								href="/product/product-list/pageno=${page.prev}">이전으로</a></li>
						</c:if>
						<c:forEach begin="${page.start}" end="${page.end}" var="i">
							<c:if test="${i==page.pageno}">
								<li class="page-item active"><a class="page-link"
									href="/product/product-list/pageno=${i}">${i}</a></li>
							</c:if>
							<c:if test="${i!=page.pageno}">
								<li class="page-item"><a class="page-link"
									href="/product/product-list/pageno=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${page.next>0}">
							<li class="page-item"><a class="page-link"
								href="/product/product-list/pageno=${page.next}">다음으로</a></li>
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
