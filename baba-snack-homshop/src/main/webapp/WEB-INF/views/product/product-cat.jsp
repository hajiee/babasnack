<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/product.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
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
					<dl class="sort">
						<dt class="blind">검색결과 정렬</dt>
						<dd>
							<ul>
								<li><a href="javascript:sendsort('sellcnt')">인기순</a></li>
								<li><a href="javascript:sendsort('regdate')">최신순</a></li>
								<li><a href="javascript:sendsort('price')">낮은가격순</a></li>
							</ul>
						</dd>
					</dl>
				</div>
				<!-- .total-sort -->

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
				<!-- 상품추출 -->
				<div class="mproduct_area">
					<ul>
						<c:forEach items="${productPage.products}" var="p">
							<li><a href="#" class="box">
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
											<span class="rev">${p.productStock}개 남음</span>
										</dd>
									</dl>
							</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id="pagination" style="display: flex; justify-content: center;">
					<ul class="pagination">
						<c:if test="${page.prev>0}">
							<li class="page-item"><a class="page-link"
								href="/product/list?pageno=${page.prev}">이전으로</a></li>
						</c:if>
						<c:forEach begin="${page.start}" end="${page.end}" var="i">
							<c:if test="${i==page.pageno}">
								<li class="page-item active"><a class="page-link"
									href="/product/list?pageno=${i}">${i}</a></li>
							</c:if>
							<c:if test="${i!=page.pageno}">
								<li class="page-item"><a class="page-link"
									href="/product/list?pageno=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${page.next>0}">
							<li class="page-item"><a class="page-link"
								href="/product/list?pageno=${page.next}">다음으로</a></li>
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
