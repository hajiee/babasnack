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

<title>BABA-SNACK - cart(작성중)</title>

<style>
#cartBenefit-form>form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

#cartProductsDetail-form>form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

#cartButton-form>form {
	text-align: center
}

.board_list {
	width: 100%;
	border-top: 2px solid #252525;
	border-bottom: 1px solid #ccc;
	margin: 15px 0;
	border-collapse: collapse;
	text-align: center
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
				<div id="cartBenefit-form" class="mt-3">
					<form>
						<h5>혜택정보</h5>
						<span>모든 상품 10% 적립 가능</span>
					</form>
				</div>

				<div id="cartProductsDetail-form" class="mt-3">
					<form>
						장바구니 정보

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

					</form>
				</div>

				<div id="cartButton-form" class="mt-3 d-grid">
					<form>
						<button type="button" class="btn btn-success btn-lg">계속
							쇼핑하기</button>
						<button type="button" class="btn btn-success btn-lg">주문하기</button>
						<button type="button" class="btn btn-success btn-lg">전체삭제(취소)</button>
					</form>
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