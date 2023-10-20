<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<title>BABA-SNACK-Join-Page</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
  .fail { color: red; }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#join').click(function() {
        	const formData = new FormData($('#join-form')[0]);

            $.ajax({
                url: '/member/join',
                method: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log('가입이 완료되었습니다.');
                    // 가입 성공 시 동작할 내용을 여기에 작성하세요.
                    $('#join-form').hide(); // 가입 폼 숨김
                    $('#join-success-msg').show(); // 가입 완료 메시지 표시
                },
                error: function(xhr, status, error) {
                    console.log('가입 중 오류가 발생했습니다.');
                    console.log(error);
                    // 가입 실패 시 동작할 내용을 여기에 작성하세요.
                }
            });
        });
    });
</script>
<script>
$(document).ready(function() {
	  $('#join').on('click', function() {
	    const password = $('#password').val();
	    const confirmPassword = $('#confirm-password').val();

	    if (password === '') {
	      alert('비밀번호를 입력하세요.');
	      return;
	    }

	    if (confirmPassword === '') {
	       alert('비밀번호 확인을 입력하세요.');
	       return;
	     }

	     if (password !== confirmPassword) {
	        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
	        return;
	     }

	     // 서버로 폼 데이터 전송 등의 작업 수행
	     $('#join-form').submit();
	   });
	});
</script>



<style>
	#join-form>form{
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
			<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		</header>
		<nav>
			<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		</nav>
		<main style="border: none">
			<h1>회원 가입</h1>
			<section id="join-form">
<form action="/member/join" method="post" enctype="multipart/form-data" id="join-form">
					<div class="mb-3 mt-3">
						<label for="username" class="form-label">아이디:</label>
						<input type="text" class="form-control" id="username" name="username" maxlength="10">
						<span id="username-msg"></span>
					</div>
					 <div class= 'mb-3 mt-3'>
      		<label for='password' class='form-label'>비밀번호:</label>
      		<input type='password' class='form-control' id='password' name='password' maxlength='10'>
     		 <!-- 비밀번호 확인 필드 추가 -->
    		<label for='confirm-password' class='form-label'>비밀번호 확인:</label>
    		<input type='password' class='form-control' id='confirm-password'>
    		<span id ='confirm-password-msg'></span>  
  			</div>  
          			
          			<div class="mb-3 mt-3">
            			<label for="email" class="form-label">이메일:</label>
            			<input type="text" class="form-control" id="email" name="email">
            			<span id="email-msg"></span>
          			</div>
          			<div class="mb-3 mt-3">
   						 <label for="pnoTell" class="form-label">전화번호:</label>
    					<input type="text" class="form-control" id="pnoTell" name="pnoTell">
    					<span id="pnoTell-msg"></span>
					</div>
          			
          				<button type ="submit" id="join" class="btn btn-primary">가입</button>
						</form>
		
				 
			</section>
		</main>
		<footer>
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>