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

<title>BABA-SNACK - pay(작성중)</title>

<style>
#payProduct-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 1400px;
}

#payMemberProfile-form {
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 800px;
}

.payBoard_list {
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

#ChoosePayBtn-form {
	border: 1px dashed saddlebrown;
	padding: 10px;
	width: 1000px;
	text-align: center;
}

#position-right {
	position: relative;
	right: 200px;
	bottom: 10px;
	font-size: 1.5em;
}

#position-left {
	position: relative;
	left: 250px;
}

#position-left2 {
	position: relative;
	left: 285px;
	bottom: 5px;
}

.input-group-text {
	width: 100px;
}
</style>

<script>
	$(document).ready(function() {
		$('#PsPay').on("click", function() {

			const name = $('#name').val();
			const pnoTell = $('#pnoTell').val();
			const delivery1_input = $('#delivery1_input').val();

			if (name === '') {
				alert('이름(성함)이 입력되어 있지 않습니다.');
				return;
			}
			if (pnoTell === '') {
				alert('전화번호가 입력되어 있지 않습니다.');
				return;
			}
			if (delivery1_input === '') {
				alert('기본 주소가 입력되어 있지 않습니다.');
				return;
			}

			alert("임시 테스트\n결제 완료 페이지로 이동");
			location.href = '/cart/order-end';

			$("#frm").appendTo($('body')).submit();
		});
	})

	$(document).ready(function() {
		$('#NaverPay').on("click", function() {

			const name = $('#name').val();
			const pnoTell = $('#pnoTell').val();
			const delivery1_input = $('#delivery1_input').val();

			if (name === '') {
				alert('이름(성함)이 입력되어 있지 않습니다.');
				return;
			}
			if (pnoTell === '') {
				alert('전화번호가 입력되어 있지 않습니다.');
				return;
			}
			if (delivery1_input === '') {
				alert('기본 주소가 입력되어 있지 않습니다.');
				return;
			}

			alert("임시 테스트\n네이버Pay 결제 완료 페이지로 이동");
			location.href = '/cart/order-end';

			$("#frm").appendTo($('body')).submit();
		});
	})

	function inputNumOnly(onlyNum) {
		onlyNum.value = onlyNum.value.replace(/[^0-9]/g, '');
	}
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
					*결제 페이지

					<div class="mt-3">
						<div id="payProduct-form" class="mt-3">

							<b>결제상품 리스트</b>
							<table class="payBoard_list">
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
								<div>
									[주문상품 총가격 : <span>??</span>원]
								</div>
								<div>
									[주문상품 총적립금 : <span>??</span>원]
								</div>
							</div>

						</div>
					</div>

					<div id="payMemberProfile-form" class="mt-3">

						<div>
							<b>수령인 정보</b>

							<div class="input-group">
								<span class="input-group-text">이름(성함)</span> <input type="text"
									id="name" class="form-control" placeholder="사용자 이름"
									maxlength="4" value="${DBOrderBuyDto.name}">
							</div>
							<div class="input-group">
								<span class="input-group-text">연락처</span> <input type="text"
									id="pnoTell" class="form-control" oninput="inputNumOnly(this)"
									placeholder="사용자 연락처" maxlength="11"
									value="${DBOrderBuyDto.pnoTell}">
							</div>
						</div>

						<div class="mt-3">
							<b>배송지 주소</b> <input type="text" class="form-control"
								id="delivery1_input" placeholder="주소"
								value="${DBOrderBuyDto.baseDelivery}">
						</div>

					</div>

					<div id="ChoosePayBtn-form" class="mt-3">

						<span id="position-left2"><b>[적립금 사용]</b> <input
							type="text" placeholder="적립금" style="width: 80px"
							disabled="disabled"> <b>→</b> <input type="text"
							style="width: 80px" id="useReserve"> <br> </span> <span
							id="position-right"><b>결제 방법</b></span> <span id="position-left">
							<button type="button" class="btn btn-success btn-lg"
								id="NaverPay">네이버 Pay</button>
							<button type="button" class="btn btn-success btn-lg" id="PsPay">결제하기</button>
						</span>

					</div>
				</form>
			</section>

		</main>

		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>




	<!-- 네이버Pay 오픈 API -->
	<!-- 
	<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
	<script>
		var oPay = Naver.Pay.create({
			"mode" : "production", // development or production
			"clientId" : "u86j4ripEt8LRfPGzQ8", // clientId
			"chainId" : "TDZSUHBoVGRFS2l" // chainId
		});

		//직접 만드신 네이버페이 결제버튼에 click Event를 할당하세요
		var elNaverPayBtn = document.getElementById("NaverPay");

		elNaverPayBtn.addEventListener("click", function() {
			oPay.open({
				"merchantUserKey" : "가맹점 사용자 식별키",
				"merchantPayKey" : "가맹점 주문 번호",
				"productName" : "상품명을 입력하세요",
				"totalPayAmount" : "1000",
				"taxScopeAmount" : "1000",
				"taxExScopeAmount" : "0",
				"returnUrl" : "사용자 결제 완료 후 결제 결과를 받을 URL"
			});
		});
	</script>
	 -->

</body>
</html>