<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
<title>BABA-SNACK - orderdetails(작성중)</title>

<style>
#orderDetailsProduct-form>form {
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
	background-color: #e3f2fd;
}

#orderDetailsAllProductPrice {
	text-align: right;
}

#orderDetailsMemberProfile-form>form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}
</style>

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
				<div class="mt-3">

					<form>*주문 상세 페이지</form>

					<div id="orderDetailsProduct-form" class="mt-3">
						<form>
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

									<tr>
										<td><img src=""> 상품이미지test</td>
										<td>상품명test</td>
										<td>가격test</td>
										<td>개수test</td>
									</tr>

									<tr>
										<td><img src=""> 상품이미지test</td>
										<td>상품명test</td>
										<td>가격test</td>
										<td>개수test</td>
									</tr>
								</tbody>
							</table>
							<div style="text-align: right;">
								[주문상품 총가격 : <span>??</span>원]
							</div>
						</form>
					</div>

					<div id="orderDetailsMemberProfile-form" class="mt-3">
						<form>
							<p><b>※ 주문자 정보</b></p>
							<ul>
								<li>아이디 : <span>??</span></li>
								<li>연락처 : <span>??</span></li>
								<li>이메일 : <span>??</span></li>
							</ul>
						</form>
					</div>


					<div style="text-align: center;" class="mt-3">
						<button type="button" class="btn btn-success btn-lg"
							onclick="location.href='/cart/pay'">주문하기</button>
					</div>

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