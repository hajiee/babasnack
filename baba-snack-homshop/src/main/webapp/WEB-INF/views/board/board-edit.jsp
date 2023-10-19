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
	if (msg !== '')
		alert(msg);
</script>
<script>
  var errorMessage = '${errorMessage}';
  if (errorMessage.trim() !== '') {
    document.write('<p>' + errorMessage + '</p>');
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

	// 폼 데이터 생성 및 전송 준비 
	var formData = new FormData();
	formData.append("title", title);
	formData.append("contents", contents);
	formData.append("code", code);

	// 폼 데이터 전송하기 
	$.ajax({
	  url: "/board-edit/${board.bno}",
	  type: "POST",
	  data: formData,
	  processData: false,
	  contentType: false,
	  success: function(response) {
	    console.log(response); 	// 성공적으로 처리된 후의 동작 정의 
	    location.href = "/board/board-list";	// 게시글 목록 페이지로 리다이렉트 
	  },
	  error: function(xhr, status, error) {
	    console.error(error);  	// 에러 발생 시의 동작 정의 
	    alert("게시글 수정에 실패했습니다. 다시 시도해주세요.");
	  }
	});
  });

  <%-- 기존 글 수정 시 기존 암호 표시 --%>
  <%-- board 객체에서 code 값을 가져와서 해당 필드에 설정 --%>
  var boardCode = '${board.code}';
  if (boardCode !== '') {
    document.write('<input type="password" name="code" value="' + boardCode + '" placeholder="암호를 입력하세요">');
  } else {
    document.write('<input type="password" name="code" placeholder="암호를 입력하세요">');
  }
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
			<aside></aside>
			<section>
				<!-- 등록된 게시글 정보 표시 -->
				<c:forEach var="board" items="${boards}">
					<div class="board-item">
						<!-- 수정 폼 -->
						<c:if test="${session.getAttribute('username') eq board.username}">
							<form action="/board-edit/${board.bno}" method="post">
								<!-- 제목 입력 필드 -->
								<input type="text" name="title" value="${board.title}">
								<!-- 내용 입력 필드 -->
								<textarea name="contents">${board.boardNotice}</textarea>
								<button type='submit' class='btn btn-outline-info'>저장</button>
								<!-- 암호 입력 필드 -->
								<input type="password" name="code" placeholder="암호를 입력하세요">
							</form>
						</c:if>

						<!-- 삭제 버튼 -->
						<c:if test="${session.getAttribute('username') eq board.username}">
							<form action="/board-delete/${board.bno}" method="post">
								<button type='button' class='btn btn-outline-danger' onclick="confirmDelete(${board.bno})">삭제</button>

								<!-- 암호 입력 폼 -->
								<div id="CodePopup_${board.bno}" class="Code-popup"	style="display: none;">
									<input type="hidden" name="bno" value="${board.bno}">
									<input type="password" id="Code_${board.bno}" placeholder="암호를 입력하세요">
									<button type="submit">확인</button>

									<!-- 암호 불일치 안내 메시지 -->
									<div id="CodeMismatch_${board.bno}" class="error-message" style="display: none;">암호가 일치하지 않습니다.</div>
								</div>
							</form>
						</c:if>
					</div>
				</c:forEach>
			</section>
			<aside></aside>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
<script>
function confirmDelete(bno) {
    document.getElementById("CodePopup_" + bno).style.display = "block";
}

function deleteBoard(bno) {
    const codeInput = document.getElementById("Code_" + bno);
    
    // 서버 측에 비밀번호 유효성 검사 요청을 보내고 결과 받아오기
    const isCodeValid = ${boardService.checkBoardCode(bno, codeInput.value)};
    
    if (!isCodeValid) {
        // 비밀번호가 일치하지 않는 경우에 대한 처리 로직
        document.getElementById("CodeMismatch_" + bno).style.display = "block";
        return false; // 폼 제출 방지
    }
    
    if (confirm("정말로 삭제하시겠습니까?")) {
        location.href = '/board/' + bno + '/delete';
        
        // 삭제 후 board-list로 이동
        location.href = '/board/board-list';
    }
}

//서버 측 변수 값을 클라이언트 측 JavaScript로 전달하기 위해 사용(JavaScript 코드는 HTML 내부에 포함되어 실행)
<p>Username: <%= session.getAttribute("username") %></p>
<p>Is User in Role ROLE_USER: <%= request.isUserInRole("ROLE_USER") %></p>

window.addEventListener('DOMContentLoaded', () => {
  const noticeInput = document.querySelector('#noticeCheckbox');

  if (isAuthenticated && username === 'admin') {
    if (noticeInput !== null) {
      // 관리자인 경우, 공지사항 체크박스 기본 선택 설정 및 클릭 이벤트 리스너 추가
      noticeInput.checked = true;

      noticeInput.addEventListener('click', () => {
        if (noticeInput.checked) {
          alert("공지로 등록되었습니다.");
        } else {
          alert("공지 등록이 해제되었습니다.");
        }
      });

      noticeInput.disabled = false; // 관리자인 경우에만 체크박스 활성화
    }
  } else {
    if (noticeInput !== null) {
      noticeInput.checked = false;
      noticeInput.disabled = true;
    }
  }
});
</script>
</html>
