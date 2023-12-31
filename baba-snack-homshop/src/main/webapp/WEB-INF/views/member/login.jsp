<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	
	<!-- JavaScript 코드 -->
<script type='text/javascript'>
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
      // 폼 데이터 제출
      submitLoginForm();
    });

    // 로그인 폼 제출 함수 정의
    function submitLoginForm() {
      var form = document.getElementById("login-form");
      // AJAX 요청으로 폼 데이터 전송
      $.ajax({
        url: "/member/login",
        method: "POST", // POST 메서드로 변경
        data: $(form).serialize(),
        success: function(response) {
          // 성공적으로 응답 받았을 때 처리할 작업 수행
          console.log(response);
          location.href = "/main";  // 예시: 다른 페이지로 이동
        },
        error: function(xhr, status, error) {
           // 요청 실패 시 처리할 작업 수행
           console.error(error);
           alert("로그인에 실패했습니다.");
        }
      });
    }
  });
</script>
	




<style>
#login-form>form{
	width:400px;
	height:500px;
	border: 1px solid saddlebrown;
	padding:10px;
}

#login-form .border{
	width:auto !important; /* 수정된 부분 */
	margin-top:10px; /* 수정된 부분 */
}
</style>

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
				<form action="/member/login" id="login-form" method="post" >
					<div class="mb-3 mt-3">
						<label for="username" class="form-label">아이디:</label>
						<input type="text" class="form-control" id="username" name="username" maxlength="10"> <span id="username-msg"></span>
					</div>
					<div class="mb-3 mt-3">
						<label for="password" class="form-label">비밀번호:</label>
						<input type="password" class="form-control" id="password" name="password" maxlength="10"> <span id="password-msg"></span>	
					</div>
					<div class="mb-3 mt-3">
            			<button type="submit" class="btn btn-primary" id="login-btn" >로그인</button>
          			</div>
          			<div class="mb-3 mt-3">
   				  <div class="d-flex">
       					<a href="/findbyid" class="btn btn-primary me-2">아이디 찾기</a>
        				<a href="/findbypw" class="btn btn-primary">비밀번호 찾기</a>
    				</div>
						</div>
          			
		 			<!-- 네이버 로그인 버튼 -->
          			<div class="mb-3 mt-3 border p-2">
            			<button type="button" class="btn btn-primary" id="naver-login-button">네이버 로그인</button>
          			</div>

	
				
				
    			</form>
			</section>
			
		</main>
		<footer>
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
		<!-- 네이버 SDK 스크립트 -->
	<script>
		function aaa(e) {
			/*
			alert('fffffff');
			e.preventDefault();
			const username = $('#username').val();
			const password = $('#password').val();
			const form = $("<form action='/member/login' method='post'>");
			$("<input type='hidden' name='username'>").val(username).appendTo(form);
			$("<input type='hidden' name='password'>").val(password).appendTo(form);
			console.log(form.serialize());
			alert('fire');
			*/
		}
	</script>
  <script src="//static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset='utf-8'></script>

  <!-- JavaScript 코드 -->
  <!-- 
  <script type='text/javascript'>
    // 네아로 SDK 초기화 완료 후 동작할 콜백 함수 등록
    window.addEventListener('load', function () {
      var naverLogin = new naver.LoginWithNaverId({
        clientId: '80mdf88flkWWJT1f93Tz',
        callbackUrl: 'http://localhost:8181/naverLogin',
        isPopup: false,
        callbackHandle: true,
      });

      // 네이버 SDK 초기화
      naverLogin.init();

      // 네이버 SDK 초기화 완료 후 동작할 콜백 함수 등록
      naverLogin.getLoginStatus(function (status) {
        if (status) {
          var email = naverLogin.user.getEmail();
          
          console.log(naverLogin.user);
          
          if (email == undefined || email == null) {
            alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
            naverLogin.reprompt();
            return;
          }
        } else {
        	console.log("callback 처리에 실패하였습니다.");
      	}
    	});

    	// 네이버 로그인 버튼 클릭 시 동작할 함수
    	document.getElementById('naver-login-button').addEventListener('click', function() {
      		naverLogin.getLoginStatus(function(status) { 
        		if (status) { 
          			console.log('Already logged in');
          			// 이미 로그인된 상태일 경우 처리할 작업 수행
          			// 예시: 다른 페이지로 이동 또는 추가 작업 수행
          			location.href = "/main";
        		} else { 
        			console.log('Not logged in'); 
        			naverLogin.reprompt(); 
      			} 
    	   });  
    	});
   });

   // 네이버 로그아웃 버튼 클릭 시 동작할 함수
   document.querySelector('.btn.btn-primary').addEventListener('click', function() {
       location.href = "https://nid.naver.com/nidlogin.logout";
   });
  
</script>
-->
	</div>
</body>
</html>