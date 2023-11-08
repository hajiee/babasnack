<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
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

<title>BABA-SNACK - 장바구니(작성중)</title>

<style>
#cartBenefit-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

#cartProductsDetail-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

.board_list {
	width: 100%;
	border-top: 2px solid #252525;
	border-bottom: 1px solid #ccc;
	margin: 5px 0;
	border-collapse: collapse;
	text-align: center
}

th {
	background-color: #93DAFF;
}

tbody tr:nth-child(2n) {
	background-color: #e3f2fd;
}
</style>

<script>
	// 주문하기 버튼 누를 때, 장바구니 안에 상품이 비어있으면 주문할 수 없게끔 기능 구현
	// 장바구니 안에 상품이 존재하면 취소가 안된다는 창(확인, 취소)을 띄우고 주문상세 페이지로 이동
	$(document)
			.ready(
					function() {
						$('#PsOrderDetails')
								.on(
										"click",
										function() {
											const username = $('#username')
													.val();
											var query = {
												username : $('#username').val()
											};

											// 특정 회원의 배송지 주소가 있는지, 없는지 확인용
											$
													.ajax({
														url : "/cart/usernameDeliveryCheck",
														type : "post",
														data : query,
														async : false,
														success : function(data) {
															if (data == 0) { // data == 0 : 배송지가 비어있는 상태
																alert("["
																		+ username
																		+ "]님의 배송지 주소가 존재하지 않습니다.\n배송지 주소 저장을 위해 배송지 페이지(마이페이지)로 이동합니다.");
																location.href = '/delivery/delivery-list';
																return false;
															} else { // data == 1 : 배송지가 있는 상태
																// 특정 회원의 장바구니 비어있는지, 아닌지 확인용
																$
																		.ajax({
																			url : "/cart/usernameCheck",
																			type : "post",
																			data : query,
																			async : false,
																			success : function(
																					data) {
																				if (data == 0) { // data == 0 : 장바구니가 비어있는 상태
																					alert("["
																							+ username
																							+ "]님의 장바구니가 비어있어 주문할 수 없습니다.");
																					return false;
																				} else { // data == 1 : 장바구니에 상품이 있는 상태
																					if (!confirm('저희 쇼핑몰의 모든 상품은 수제로 만든 간식이며,\n주문하시게 되면 주문취소를 할 수가 없습니다.\n이대로 주문을 진행하시겠습니까?')) {
																						return false;
																					} else {
																						location.href = '/cart/orderdetails';
																					}
																				}
																			}
																		}); // ajax 끝														
															}

														}
													}); // ajax 끝

										});

					});

	// 상품 전체삭제	
	$(document).ready(
			function() {
				$('#PsCartDelete').on(
						"click",
						function() {
							$('#frm').attr('action',
									'/cart/orderdetails-list/delete').attr(
									'method', 'post').submit();
							alert("장바구니의 상품들을 전부 비우겠습니다");
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
		<main style="border: none">
			<section>
				<form id="frm" name="frm">
					<div id="cartBenefit-form" class="mt-3">
						<h5>
							<b>혜택정보</b>
						</h5>
						<ol>
							<li>모든 상품 10% 적립 가능</li>
						</ol>
					</div>
					<div id="cartProductsDetail-form" class="mt-3">
						<b>장바구니 상품</b>
						<table class="board_list">
							<colgroup>
								<col width="25%">
								<col width="25%">
								<col width="25%">
								<col width="25%">
							</colgroup>
							<thead>
								<tr>
									<th>상품이미지</th>
									<th>상품명</th>
									<th>가격</th>
									<th>개수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cartDto.cart}" var="cart">
									<tr>
										<td>												
													<img  src="/productImg/${cart.productSaveImg}" alt="상품이미지"
														width="100px"><br>
											 확인용 pno : ${cart.pno}</td>
										<td>${cart.productName}</td>
										<td>${cart.productPrice}</td>
										<td>${cart.productCnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div style="text-align: right;">
							[장바구니 상품 총가격 : <span>${cartDto.allPrice}</span>원]
						</div>
					</div>

					<div style="text-align: center;" class="mt-3">
						<button type="button" class="btn btn-success btn-lg"
							onclick="location.href='/'">계속 쇼핑하기</button>
						<button type="button" class="btn btn-success btn-lg"
							id="PsOrderDetails">주문하기</button>
						<button type="button" class="btn btn-success btn-lg"
							id="PsCartDelete">전체삭제(취소)</button>
					</div>
				</form>

				<div>
					<label>*회원 로그인 아이디 script 적용 : </label> <input type="text"
						id="username" name="username" readonly="readonly"
						value="<sec:authentication property="principal.username"/>">
				</div>
			</section>

		</main>

		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>

</body>
</html>