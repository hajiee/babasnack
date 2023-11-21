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

<title>BABA-SNACK - 구매완료(작성중)</title>

<style>
#orderEndDetailsProduct-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

.orderEndDetailsBoard_list {
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

#paySelect {
	border: 1px dashed saddlebrown;
	padding: 32px 0;
	height: 100px;
	width: 300px;
	position: relative;
	right: 500px;
	text-align: center;
	height: 100px;
}

#orderMemberInfo-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

#orderDeliveryInfo-form {
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
				<form id="frm" name="frm">
					*구매완료 페이지

					<div id="orderEndDetailsProduct-form" class="mt-3">
						<table class="orderEndDetailsBoard_list">
							<colgroup>
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
							</colgroup>
							<thead>
								<tr>
									<th>상품 이미지</th>
									<th>상품명</th>
									<th>가격</th>
									<th>개수</th>
									<th>상품정보</th>
								</tr>
							</thead>
							<tbody>

								<tr>
									<td><img src=""> 상품이미지test</td>
									<td>상품명test</td>
									<td>가격test</td>
									<td>개수test</td>
									<td><button type="button" class="btn btn-primary btn-sm">
											<b>주문 정보</b>
										</button></td>
								</tr>

								<tr>
									<td><img src=""> 상품이미지test</td>
									<td>상품명test</td>
									<td>가격test</td>
									<td>개수test</td>
									<td><button type="button" class="btn btn-primary btn-sm">
											<b>주문 정보</b>
										</button></td>
								</tr>
							</tbody>
						</table>
						<div style="text-align: right;">
							<div>
								[구매상품 총개수 : <span>??</span>개]
							</div>
							<div>
								[구매상품 최종가격 : <span>??</span>원]
							</div>
							<div>
								[구매상품 총적립금 : <span>??</span>원]
							</div>
						</div>
					</div>

					<div id="paySelect" class="mt-3">
						<h3>
							<b>결제 방법</b>
						</h3>
					</div>

					<div id="orderMemberInfo-form" class="mt-3">
						<b>◎ 주문자 정보</b>

						<div>아이디, 이름, 연락처, 이메일, 배송지</div>

					</div>

					<div id="orderDeliveryInfo-form">
						<b>◎ 배송 정보</b>
						<div>배송정보(관리자 수정에 의해 변경)</div>
					</div>

					<div class="mt-3" style="text-align: right">
						<button type="button" class="btn btn-outline-secondary btn-lg"
							onclick="location.href=''">
							<b>주문상세</b>
						</button>
						<button type="button" class="btn btn-outline-secondary btn-lg"
							onclick="location.href='/'">
							<b>확인</b>
						</button>
					</div>
				</form>


			</section>

		</main>

		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>

</body>
</html>