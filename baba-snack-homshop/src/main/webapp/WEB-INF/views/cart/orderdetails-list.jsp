<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	// 주문하기 버튼 누를 때, 취소가 안된다는 창(확인, 취소)을 띄우고 주문상세 페이지로 이동
	function PsOrderDetails() {
		if (!confirm('저희 쇼핑몰의 모든 상품은 수제로 만든 간식이며,\n주문하시게 되면 주문취소를 할 수가 없습니다.\n이대로 주문을 진행하시겠습니까?')) {
			return false;
		} else {
			location.href = '/cart/orderdetails';
		}
	}
		
	
	// 상품 전체삭제	
	$(document).ready(function() {
		$('#PsCartDelete').on("click", function() {
			const form = $('<form>').attr('action','/cart/orderdetails-list/delete').attr('method','post');	
			form.appendTo($('body')).submit();
			alert("전체삭제 테스트");			
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
				<div id="cartBenefit-form" class="mt-3">
					<form>
						<h5>
							<b>혜택정보</b>
						</h5>
						<ol>
							<li>모든 상품 10% 적립 가능</li>
						</ol>
					</form>
				</div>
				<div id="cartProductsDetail-form" class="mt-3">
					<form action="" method="get">
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
										<td><img src="${$cart.productSaveImg}" width="60px">상품이미지test</td>
										<td>상품명test ${$cart.productName}</td>
										<td>가격test ${$cart.productPrice}</td>
										<td>개수test ${$cart.productCnt}</td>
									</tr>

									<tr>
										<td><img src="">상품이미지test</td>
										<td>상품명test</td>
										<td>가격test</td>
										<td>개수test</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div style="text-align: right;">
							[장바구니 상품 총가격 : <span>${cartDto.allPrice}</span>원]
						</div>

					</form>
				</div>

				<div style="text-align: center;" class="mt-3">
					<form>
						<button type="button" class="btn btn-success btn-lg"
							onclick="location.href='/'">계속 쇼핑하기</button>
						<button type="button" class="btn btn-success btn-lg"
							onclick="PsOrderDetails()">주문하기</button>
						<button type="button" class="btn btn-success btn-lg"
							id="PsCartDelete">전체삭제(취소)</button>
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