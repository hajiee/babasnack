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
<title>BABA-SNACK-dog</title>
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
			<aside id="best">
				<!-- 베스트상품 -->
				<jsp:include page="/WEB-INF/views/include/aside-best.jsp" />
			</aside>
			<section>
				<!-- 메인 상품 -->
				<h3 class="blind">강아지 간식상품</h3>
				<div class="total-sort">
					<p class="total" style="color: #a2a2a2;">
						In this category are <strong>DOG</strong> products.
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

				<!-- 상품추출 -->
				<div class="mproduct_area">
					<ul>
						<c:forEach items="${productPage.products}" var="p"><!-- 상세 페이지로 이동하는 링크 -->
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
											<span class="rev">${p.productStock}개 남음</span>
										</dd>
									</dl>
							</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id=pagination style="display: flex; justify-content: center;">
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
