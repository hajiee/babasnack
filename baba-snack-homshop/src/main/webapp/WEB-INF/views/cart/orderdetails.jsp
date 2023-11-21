<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>BABA-SNACK - 주문상세(작성중)</title>

<style>
#orderDetailsProduct-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

.orderDetailsBoard_list {
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

#orderDetailsAllProductPrice {
	text-align: right;
}

#orderDetailsMemberProfile-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}
</style>

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
		<main style="border: none">
			<section>
				<form id="frm" name="frm">
					<div class="mt-3">

						*주문 상세 페이지

						<div id="orderDetailsProduct-form" class="mt-3">

							<b>주문상세 리스트</b>

							<table class="orderDetailsBoard_list">
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
									<c:forEach items="${ODcartDto.cart}" var="cart">
										<tr>
											<td><img src="/productImg/${cart.productSaveImg}"
												alt="상품이미지" width="150px"></td>
											<td>${cart.productName}</td>
											<td>${cart.productPrice}</td>
											<td>${cart.productCnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div style="text-align: right;">
								[장바구니 상품 총개수 : <span>${ODcartDto.productCnt}</span>개, 총가격 : <span>${ODcartDto.allPrice}</span>원]<br>
							</div>

						</div>

						<div id="orderDetailsMemberProfile-form" class="mt-3">

							<b>※ 주문자 정보</b>

							<ul>
								<li><b>아이디 :</b> <span><sec:authentication
											property="principal.username" /></span></li>
								<li><b>연락처 :</b> <span>${ODMemberDto.pnoTell}</span></li>
								<li><b>이메일 :</b> <span>${ODMemberDto.email}</span></li>
							</ul>
						</div>


						<div style="text-align: center;" class="mt-3">
							<button type="button" class="btn btn-success btn-lg"
								style="width: 180px;" onclick="location.href='/cart/pay'">주문하기</button>
						</div>

					</div>
				</form>

				<div>
					<!-- *회원 로그인 아이디 확인용 -->
					<input type="hidden" id="username" name="username"
						readonly="readonly"
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