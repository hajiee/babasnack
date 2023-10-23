<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board-write.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>게시판 글등록</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '${msg}';
	if (msg !== '') {
			alert("문의글 작성은 회원만 작성가능합니다");
			window.location.href = "/member/login"; // 로그인 페이지로 리다이렉트
	}
</script>
<script>
$(document).ready(function() {
	  // 등록 버튼 클릭 시 동작
	  $('#submitBtn').click(function(e) {
	    e.preventDefault(); // 기본 동작(폼 제출) 막기

	 	// 암호 입력 여부 확인
        var code = $('#exampleFormControlInput2').val();

        if (code.trim() === '') { // 암호가 비어있는 경우
            alert('암호를 입력해주세요.');
            $('#exampleFormControlInput2').focus(); // 커서를 암호 필드로 이동
            return;
        }

        if (!/^\d{4}$/.test(code)) { // 숫자 4자리가 아닌 경우
            alert('숫자 4자리로 입력해주세요.');
            $('#exampleFormControlInput2').focus(); // 커서를 암호 필드로 이동
            return;
        }
	    
	    // 제목과 내용 입력 여부 확인
	    var title = $('#exampleFormControlInput1').val();
	    var contents = $('#exampleFormControlTextarea1').val();

	    if (title.trim() === '') { // 제목이 비어있는 경우
	      alert('제목을 입력해주세요.');
	      $('#exampleFormControlInput1').focus(); // 커서를 제목 필드로 이동
	      return;
	    }

	    if (contents.trim() === '') { // 내용이 비어있는 경우
	      alert('내용을 입력해주세요.');
	      $('#exampleFormControlTextarea1').focus(); // 커서를 내용 필드로 이동
	      return;
	    }

	    // 폼 제출하기
	    document.getElementById('boardForm').submit();
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
		<main id="board-write-css">
			<aside>
			</aside>
			<section>
				<form id="boardForm" action='<c:url value='/board/boardInsert'/>' method="post">
					<div class="form-group">
						<label for="noticeCheckbox" style="margin: 10px; color: darkgray;">공지여부</label>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<input type="checkbox" id="noticeCheckbox" name="notice">
						</sec:authorize>
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<input type="checkbox" id="noticeCheckbox" name="notice" disabled>
						</sec:authorize>
					</div>
					<div class="form-group">
						<input type="password" class="form-control"	id="exampleFormControlInput2" name="code" placeholder="암호설정(숫자 4개)">
					</div>
					<div class="form-group">
						<label for="exampleFormControlInput1" style="margin: 10px; color: darkgray;">제목</label>
						<input type="text" class="form-control" id="exampleFormControlInput1"
							name="title" placeholder="제목을 작성해주세요.">
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1" style="margin: 10px; color: darkgray;">내용</label>
						<textarea class="form-control" id="exampleFormControlTextarea1" name="contents" placeholder="내용을 작성해주세요." rows="10"></textarea>
					</div>
					<div class="text-right">
						<button type='button' class='btn btn-outline-info' onclick="submitForm('/board/board-write')">등록</button>
					</div>
				</form>
			</section>
			<aside>
			</aside>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
<script type='text/javascript'>
    	var isAuthenticated = <%=session.getAttribute("isAuthenticated")%>;
    	var username = '<%=session.getAttribute("username")%>';
    	var isAdmin = <%=request.isUserInRole("ROLE_ADMIN")%>;

        // 비회원인 경우 처리
        if (!isAuthenticated && !isAdmin) {
            alert('로그인 후 이용해주세요.');
            window.location.href = '/member/login';  // 로그인 페이지로 리다이렉트
        }
</script>
<script type='text/javascript'>
      function submitForm(action) {
          document.getElementById('boardForm').action = action;
          document.getElementById('boardForm').submit();
      }

      var isAdmin = <%= request.isUserInRole("ROLE_ADMIN") %>;
      
      window.addEventListener('DOMContentLoaded', () => {
          const noticeInput = document.querySelector('#noticeCheckbox');

          if (isAdmin) {
              if (noticeInput !== null) {
                  // 관리자인 경우, 공지사항 체크박스 활성화
                  noticeInput.disabled = false;
              }
          } else {
              if (noticeInput !== null) {
                  // 관리자가 아닌 경우, 공지사항 체크박스 비활성화
                  noticeInput.disabled = true;
              }
          }
      });
</script>
</html>
