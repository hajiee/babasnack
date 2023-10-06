<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		const state = location.search.substr(1);
		if (state === 'error')
			alert('로그인에 실패했습니다');
		$('#login').on('click', function() {
			if ($('#username').val() == '') {
				$('#username-msg').text('아이디를 입력하세요').css('color', 'red');
				return;
			}
			if ($('#password').val() == '') {
				$('#password-msg').text('비밀번호를 입력하세요').css('color', 'red');
				return;
			}
			$('#login-form').submit();
		})

	})
</script>
<style>
	#login-form>form{
		width:400px;
		height:500px;
		border: 1px solid  saddlebrown;
		padding: 10px;
	}
</style>
</head>
<body>
	<div id="page">
		<header>
			<jsp:include page="/WEB-INF/views/include/header.jsp" />
		</header>
		<nav>
			<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		</nav>
		<main style="border: none">
			<section id="login-form">
				<form action="/member/login" id="login-form" method="post">
					<div class="mb-3 mt-3">
						<label for="username" class="form-label">아이디:</label>
						<input type="text" class="form-control" id="username" name="username" maxlength="10"> <span id="username-msg"></span>
					</div>
					<div class="mb-3 mt-3">
						<label for="password" class="form-label">비밀번호:</label>
						<input type="password" class="form-control" id="password" name="password" maxlength="10"> <span id="password-msg"></span>
					</div>
					<div class="mb-3 mt-3">
						<button type="button" class="btn btn-primary" id="login">로그인</button>
					</div>
				</form>
			</section>
		</main>
		<footer>
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>