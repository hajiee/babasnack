<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board-css/board.css">
<link rel="stylesheet" href="/css/product-css/product-read.css">
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
<script>
$(document).ready(function() {
	// 재고 표시
    var product = ${product};
    var stockMessage;
    if (product.productStock >= 0) {
        var remainingStock = product.productStock - product.productCnt;
        if (product.productCnt === 0 || product.productCnt === null) {
            stockMessage = product.productStock + "개 남음";
        } else {
            stockMessage = remainingStock + "개 남음";
        }
    } else {
        stockMessage = "재고 없음";
    }
    
    // 재고 표시 업데이트 함수
    function updateStockMessage() {
        if (product.productStock >= 0) {
            var remainingStock = product.productStock - $("#quantity").val();
            if ($("#quantity").val() === "0" || $("#quantity").val() === null) {
                stockMessage = product.productStock + "개 남음";
            } else {
                stockMessage = remainingStock + "개 남음";
            }
        } else {
            stockMessage = "재고 없음";
        }
        $("#stock").text(stockMessage);
    }
    
    updateStockMessage(); // 초기 재고 표시
    
    // 수량 선택
    $("#quantity").on("change", function() {
        updateStockMessage(); // 재고 표시 업데이트
    });
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
	<main id="board-main" >
		<aside>
		</aside>
		<section>
		<sec:authorize access="hasRole('ADMIN')">
			<a href="/product/admin-product">
				<button type='button' class='btn btn-outline-dark'>상품관리</button>
			</a>
		</sec:authorize>
				<div id="get-productRead-full">
					<table id="get-productRead">
						<tr>
							<th colspan='3'>
								<h2 id="productTitle">${product.productName}</h2>
							</th>
						</tr>
						<tr>
							<td rowspan='6' id="get-productPhoto-page">
								<img id="get-productPhoto" src="" alt="${product.productName} 사진">
							</td>
						</tr>
						<tr>
							<td>가격: ${product.productPrice}원</td>
						</tr>
						<tr>
							<td id="get-productStock">재고:</td>
						</tr>
						<tr>
							<td>용량: ${product.productSize}</td>
						</tr>
						<tr>
							<td>리뷰: ${product.reviewCount} / 평균 별점:${product.reviewStar}</td>
						</tr>
						<tr>
							<td>
								<select id="quantity" name="productCnt">
									<option value="0">구매수량선택</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</td>
							<td>
								<div class="btn-group">
								<a href="/cart/orderdetails-list">
  									<button type="button" class="btn btn-secondary">장바구니</button>
  								</a>
  									<button type="button" class="btn btn-dark">구매하기</button>
  								</div>
							</td>
						</tr>
					</table>
					<hr>
					<div id="get-productNotice-full">${product.productNotice}</div>
					<hr>
					<div id="reviewPage">
					  <sec:authorize access="hasRole('ROLE_BUYER')">
						<form id="review-form" action="/product/${product.pno}/reviews" method="post" enctype="multipart/form-data">
							<input type="text" name="writer" placeholder="작성자" value="${username}"disabled>
							<input type="number" name="rating" placeholder="평점" required>
							<!-- 파일 선택 시 미리보기할 이미지 영역 -->
							<img id='review-preview' src='' alt=''	style='max-width: 200px; max-height: 100px; border: 1px dotted black;' />
							<!-- 파일 선택 필드 -->
							<input type="file" class="form-control-file" name="reviewPhotos" multiple="multiple"><br>
							<textarea id="add-reviewNotice" name="reviewNotice" placeholder="리뷰 내용" required></textarea>
							<button type="submit" class="btn btn-outline-warning">리뷰 등록</button>
						</form>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<form id="review-form" action="/product/${product.pno}/reviews" method="post" enctype="multipart/form-data">
							<input type="text" name="writer" value="관리자" disabled>
        					<textarea id="add-reviewNotice" name="reviewNotice" placeholder="댓댓글 내용" required></textarea>
        					<button type="submit"  class="btn btn-outline-dark">관리자리뷰 작성</button>
						</form>
						</sec:authorize>
						<div id="reviewPage-list">
							<h3>----- 리뷰 ------------------------------------------------------------------------------------------------------------------------------</h3>
							<ul id="review-list"></ul>
						</div>
					</div>
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