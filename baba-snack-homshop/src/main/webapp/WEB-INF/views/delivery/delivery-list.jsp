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
<title>BABA-SNACK - delivery(작성중)</title>

<style>
#deliveryNameBox-form {
	position: relative;
	right: 600px;
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 200px;
	text-align: center;
}

#deliveryList1-form {
	position: relative;
	right: 25px;
	width: 750px;
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 750px;
}

#deliveryList2-form {
	position: relative;
	right: 25px;
	width: 750px;
	border: 1px solid saddlebrown;
	padding: 10px;
}

#deliveryNamePhone-form {
	position: relative;
	left: 25px;
	width: 600px;
	border: 1px solid saddlebrown;
	padding: 10px;
}
</style>

<!-- 다음 우편 주소 api-->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	// 다음 우편 주소 api - 주소1(기본주소)
	function sample5_execDaumPostcode1() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address + ", [상세주소] : "; // 최종 주소 변수

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("baseDelivery").value = addr;
			}
		}).open();
	}

	// 다음 우편 주소 api - 주소2
	function sample5_execDaumPostcode2() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address + ", [상세주소] : "; // 최종 주소 변수

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("addDelivery").value = addr;
			}
		}).open();
	}
</script>

<script>
	$(document).ready(
			function() {
				$('#PsMain').on(
						"click",
						function() {
							const name = $('#name').val();
							const pnoTell = $('#pnoTell').val();
							const baseDelivery = $('#baseDelivery').val();

							const username = $('#username').val();

							if (name === '') {
								alert('이름(성함)을 입력해 주십시오');
								return;
							}
							if (pnoTell === '') {
								alert('전화번호를 입력해 주십시오');
								return;
							}
							if (baseDelivery === '') {
								alert('기본 주소를 입력해 주십시오');
								return;
							}


							$('#frm')
									.attr('action', '/delivery/add/{username}')
									.attr('method', 'post').submit();

							alert("임시 테스트\n메인 페이지로 이동");
						});

				$('#PsChangeAd').on(
						"click",
						function() {
							const name = $('#name').val();
							const pnoTell = $('#pnoTell').val();
							const baseDelivery = $('#baseDelivery').val();

							if (name === '') {
								alert('이름(성함)을 입력해 주십시오');
								return;
							}
							if (pnoTell === '') {
								alert('전화번호를 입력해 주십시오');
								return;
							}
							if (baseDelivery === '') {
								alert('기본 주소를 입력해 주십시오');
								return;
							}

							$('#frm').attr('action',
									'/delivery/change/{username}').attr(
									'method', 'post').submit();

							alert("배송지 수정 되었습니다\n마이 페이지(임시로 메인페이지)로 이동합니다");
						});
			});

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
					<div id="deliveryNameBox-form" class="mt-3">
						<b>배송지 주소</b>
					</div>

					<div style="float: left;">

						<div id="deliveryList1-form" class="mb-3 mt-3">
							<div>
								<h5>
									<b>주소1(기본주소)</b>
								</h5>
								<button type="button" class="btn btn-primary" id="PsAdd1"
									onclick="sample5_execDaumPostcode1()">주소 검색</button>
								<div class="mb-3 mt-3">
									<input type="text" class="form-control" id="baseDelivery"
										placeholder="주소1(기본주소)" name="baseDelivery">
								</div>
							</div>
						</div>

						<div id="deliveryList2-form" class="mb-3 mt-3">
							<h5>
								<b>주소2</b>
							</h5>
							<button type="button" class="btn btn-primary" id="PsAdd2"
								onclick="sample5_execDaumPostcode2()">주소 검색</button>
							<div class="mb-3 mt-3">
								<input type="text" class="form-control" id="addDelivery"
									placeholder="주소2" name="addDelivery">
							</div>
						</div>
					</div>


					<div style="float: left;">
						<div id="deliveryNamePhone-form" class="mb-3 mt-3">
							<label><b>이름</b></label> <input type="text" class="form-control"
								id="name" name="name" placeholder="사용자 이름"> <label
								class="mt-3"><b>연락처</b> ( - 없이 입력)</label> <input type="text"
								class="form-control" id="pnoTell" name="pnoTell"
								oninput="inputNumOnly(this)" placeholder="사용자 연락처">

							<div class="mb-3 mt-3">
								<button type="button" id="PsMain" class="btn btn-primary">배송지
									확인(저장)</button>
								<button type="button" id="PsChangeAd" class="btn btn-primary">배송지
									수정</button>
							</div>
						</div>
					</div>

					<div style="float: left;">
						<label>테스트 아이디 : </label> <input type="text" class="form-control"
							id="username" name="username" placeholder="사용자 아이디">
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