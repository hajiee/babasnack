<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board-css/board-read.css">
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
<!-- 에러 메시지 출력 -->
<script>
  var errorMessage = '${errorMessage}';
  if (errorMessage.trim() !== '') {
    document.write('<p>' + errorMessage + '</p>');
  }
</script>
<!-- 리뷰 작성 후 게시글 업데이트 -->
<script type='text/javascript'>
    // 리뷰 작성 후 게시글 업데이트 함수
    function updateBoard(boardId) {
        $.ajax({
            url: '/update-board/' + bno,
            type: 'GET',
            success: function(response) {
                // 업데이트된 게시글 정보로 화면 갱신
                $('#board-item-' + bno).html(response);
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert('게시글 업데이트에 실패했습니다.');
            }
        });
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
		<main id="board-write-css">
			<aside></aside>
			<section>
				<!-- 등록된 게시글 정보 표시 -->
				<c:forEach var="board" items="${boards}">
					<div class="board-item">
						<!-- 제목 출력 -->
						<span>${board.bno}</span>
						<h3>${board.title}</h3>
						<!-- 작성자 출력 -->
						<p>${board.boardWriter}</p>
						<!-- 내용 출력 -->
						<p>${board.boardNotice}</p>
						<!-- 관리자인 경우에만 답변과 답변 등록 버튼 표시 -->
						<c:if test="${session.getAttribute('isAdmin')}">
							<c:if test="${not empty board.boardReview}">
								<h4>관리자 답변</h4>
								<c:forEach var="reply" items="${board.boardReview}">
									<div class="reply-item">
										<!-- 관리자명 출력 -->
										<span>${reply.admin}</span>
										<!-- 관리자 답변 내용 출력 -->
										<p>${reply.adminNotice}</p>
									</div>
								</c:forEach>
							</c:if>
							<button type="submit" class="btn btn-primary" onclick="saveForm()">답변등록</button>
						</c:if>
						<!-- 수정폼으로 이동 -->
						<c:if test="${session.getAttribute('username') eq board.username}">
							<button type="button" class="btn btn-outline-info" onclick="editBoard(${board.bno})">수정</button>
							<!-- 암호 입력 폼 -->
							<div id="CodePopup_${board.bno}" class="Code-popup"
								style="display: none;">
								<form onsubmit="event.preventDefault(); checkCode(${board.bno})">
									<input type="hidden" name="bno" value="${board.bno}"> <input
										type="password" id="Code_${board.bno}" placeholder="암호를 입력하세요">
									<button type="submit">확인</button>
								</form>
								<!-- 암호 불일치 안내 메시지 -->
								<div id="CodeMismatch_${board.bno}" class="error-message"
									style="display: none;">암호가 일치하지 않습니다.</div>
							</div>
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
// 저장 버튼 클릭 시 폼 전송 함수
function saveForm() {
    document.getElementById("myForm").submit();
}
</script>
<script>
function editBoard(bno) {
    document.getElementById("CodePopup_" + bno).style.display = "block";
}

function checkCode(bno) {
    const codeInput = document.getElementById("Code_" + bno);
    
    // 서버 측에 비밀번호 유효성 검사 요청을 보내고 결과 받아오기
    $.ajax({
      url: "/board/check-code",
      type: "POST",
      data: { bno: bno, code: codeInput.value },
      success: function(response) {
        if (response === true) {
          // 암호가 일치하는 경우, 게시글 수정 페이지로 이동
          location.href = '/board/' + bno + '/board-edit';
        } else {
          // 비밀번호가 일치하지 않는 경우에 대한 처리 로직
          document.getElementById("CodeMismatch_" + bno).style.display = "block";
        }
      },
      error: function(xhr, status, error) {
        console.error(error);  	// 에러 발생 시의 동작 정의 
        alert("암호 유효성 검사에 실패했습니다.");
      }
    });
}

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
</html>
