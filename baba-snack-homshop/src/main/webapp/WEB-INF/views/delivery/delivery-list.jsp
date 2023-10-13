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
#deliveryNameBox-form>form {
	position: relative;
	right: 600px;
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 200px;
	text-align: center;
}

#deliveryList1-form>form {
	position: relative;
	right: 25px;
	width: 750px;
	border: 1px solid saddlebrown;
	padding: 10px;
	width: 750px;
}

#deliveryList2-form>form {
	position: relative;
	right: 25px;
	width: 750px;
	border: 1px solid saddlebrown;
	padding: 10px;
}

#deliveryNamePhone-form>form {
	position: relative;
	left: 25px;
	width: 600px;
	border: 1px solid saddlebrown;
	padding: 10px;
}

#displayFlex-form>form {
	display: flex;
}
</style>

<!-- 다음 우편 주소 api-->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 다음 우편 주소 api - 주소1(기본주소) -->
<script>
	function sample5_execDaumPostcode1() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address + ", [상세주소] : "; // 최종 주소 변수

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("delivery1_input").value = addr;
			}
		}).open();
	}
</script>

<!-- 다음 우편 주소 api - 주소2 -->
<script>
	function sample5_execDaumPostcode2() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address + ", [상세주소] : "; // 최종 주소 변수

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("delivery2_input").value = addr;
			}
		}).open();
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
		<main>
			<section>
				<div id="deliveryNameBox-form" class="mt-3">
					<form>배송지 주소</form>
				</div>


				<div style="float: left;">

					<div id="deliveryList1-form" class="mb-3 mt-3">
						<form action="/delivery/delivery-list" method="post">
							<h5>주소1(기본주소)</h5>

							<button type="button" class="btn btn-primary" id="delivery1_btn"
								onclick="sample5_execDaumPostcode1()">주소 검색</button>
							<div class="mb-3 mt-3">
								<input type="text" class="form-control" id="delivery1_input"
									placeholder="주소1(기본주소)" name="delivery1_input">
							</div>
						</form>
					</div>

					<div id="deliveryList2-form" class="mb-3 mt-3">
						<form action="/delivery/delivery-list" method="post">
							<h5>주소2</h5>
							<button type="button" class="btn btn-primary" id="delivery2_btn"
								onclick="sample5_execDaumPostcode2()">주소 검색</button>
							<div class="mb-3 mt-3">
								<input type="text" class="form-control" id="delivery2_input"
									placeholder="주소2" name="delivery2_input">
							</div>
						</form>
					</div>

				</div>


				<div style="float: left;">
					<div id="deliveryNamePhone-form" class="mb-3 mt-3">
						<form action="/delivery/delivery-list" method="post">
							<label for="userName">이름 : </label> <input type="text"
								class="form-control" id="userName" placeholder="사용자 이름">
							<label for="phoneNum" class="mt-3">연락처 : </label> <input
								type="text" class="form-control" id="phoneNum"
								placeholder="사용자 연락처">

							<div class="mb-3 mt-3">
								<button type="button" class="btn btn-primary">배송지 확인</button>
								<button type="button" class="btn btn-primary">배송지 수정</button>
							</div>
						</form>
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