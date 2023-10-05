<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>Insert title here</title>
<style>
  .fail { color: red; }
</style>
<script src="/script/join.js"></script>
<script>
	const formData = new FormData(document.querySelector('#join-form'));
	$.ajax({
		url:'http://localhost:8081/',
		method:'post',
		data:formData,
		processData:false,
		contentType: false
	})
</script>
</head>
<body>
	<div id="page">
		<header>
			<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		</header>
		<nav>
			<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		</nav>
		<main>
			<aside>
				<jsp:include page="/WEB-INF/views/include/aside.jsp"/>
			</aside>
			<section>
				<h1>회원 가입</h1>
				<form action="/member/join" method="post" enctype="multipart/form-data" id="join-form">
					<div class="mb-3 mt-3">
						<label for="username" class="form-label">아이디:</label>
						<input type="text" class="form-control" id="username" name="username" maxlength="10">
						<span id="username-msg"></span>
					</div>
					<div class="mb-3 mt-3">
           				<label for="password" class="form-label">비밀번호:</label>
            			<input type="password" class="form-control" id="password" name="password" maxlength="10">
            			<span id="password-msg"></span>
          			</div>
          			<div class="mb-3 mt-3">
            			<label for="email" class="form-label">이메일:</label>
            			<input type="text" class="form-control" id="email" name="email">
            			<span id="email-msg"></span>
          			</div>
          				<button type="button" id="join" class="btn btn-primary">가입</button>
				</form>
			</section>
			<aside>
				<jsp:include page="/WEB-INF/views/include/aside.jsp"/>
			</aside>
		</main>
		<footer>
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>