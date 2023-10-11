<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
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
$(document).ready(function() {
   // 재고 표시
   var products = ${productPage.products};
   for (var i = 0; i<products.length; i++) {
      var product = products[i];
      var stockMessage;
      if (product.productStock >= 0) {
         stockMessage = product.productStock + "개 남음";
      } else {
         stockMessage = "재고 없음";
      }
      $(".prdlist_default .price_box:eq("+i+")").append("<span>"+stockMessage+"</span>");
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
				<!-- 베스트상품 -->
				<jsp:include page="/WEB-INF/views/include/aside-best.jsp" />
			</aside>
			<section>
				<!-- 메인 상품 -->
				<h3 class="blind">상품 정보, 정렬</h3>
				<div class="total-sort">
					<!-- total-sort 내용 추가 -->
				</div>

				<!-- 상품리스트 -->
				<div class="prdlist_default">
					<!-- productPage.products의 각 상품에 대해 반복 -->
					<c:forEach items="${productPage.products}" var="product">
						<!-- 각 상품 정보 표시 -->
						<a href="#" class="box"> <!-- 이미지 표시 -->
							<div class="img">
								<img src="${product.Photos}" alt="">
							</div> <!-- 상품 정보 표시 -->
							<dl>
								<dt>${product.productName}</dt>
								<dd class="txt">${product.productSize}</dd>
								<dd class="price_box">
									<p>
										<span class="price">${product.productPrice}</span>
									</p>
									<!-- 재고 표시는 JavaScript로 동적으로 처리하도록 설정 -->
								</dd>
							</dl>
						</a>
					</c:forEach>
				</div>
			</section>
			<aside>
				<!-- 광고 -->
				<jsp:include page="/WEB-INF/views/include/aside.jsp" />
			</aside>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>
