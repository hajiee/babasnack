<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<title>BABA-SNACK-FindById</title>
<style>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script type='text/javascript'>
	$(document).ready(function(){
		$('#find-id-btn').click(function(){
			var name = $('#name').val();
			var email = $('#email').val();
			
			// 아이디 찾기 로직
			$.ajax({
				url: 'member/findbyid', // 아이디 찾기를 처리하는 서버의 URL
				method: 'POST',
				data: {
					name: name,
					email: email
				},
				success: function(response) {
					// 아이디 찾기 결과를 처리하는 로직
					if (response.success) {
						var userId = response.userId;
						alert('찾으시는 아이디는 ' + userId + '입니다.');
					} else {
						alert('일치하는 정보가 없습니다.');
					}
				},
				error: function(xhr, status, error) {
					console.error(error);
					alert('아이디 찾기에 실패했습니다.');
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="page">
		<header>
			<jsp:include page="/WEB-INF/views/include/header.jsp" />
		</header>
		<main style="border: none">
			<section id="find-id-form">
				<form action="#" id="find-id-form" method="post">
					<div class="mb-3">
						<label for="name" class="form-label">이름:</label>
						<input type="text" class="form-control" id="name" name="name" maxlength="10">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">이메일:</label>
						<input type="email" class="form-control" id="email" name="email">
					</div>
					<div class="mb-3">
						<button type="button" class="btn btn-primary" id="find-id-btn">아이디 찾기</button>
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
