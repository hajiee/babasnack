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
$(document).ready(function() {
	  // 등록 버튼 클릭 시 동작
	  $('#submitBtn').click(function(e) {
	    e.preventDefault(); // 기본 동작(폼 제출) 막기

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
<style>

</style>
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
						<input type="checkbox" id="noticeCheckbox" name="notice">
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

						<!-- 수정 버튼 -->
						<button type='button' class='btn btn-outline-info' onclick="editBoard(${board.bno})">수정</button>

						<!-- 삭제 버튼 -->
						<button type='button' class='btn btn-outline-danger' onclick="deleteBoard(${board.bno})">삭제</button>
					</div>

					<c:forEach var="board" items="${boardes}">
						<div class="board-item">
							<h3>${board.title}</h3>
							<p>${board.contents}</p>
						</div>
					</c:forEach>
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
<script>
function submitForm(action) {
    document.getElementById('boardForm').action = action;
    document.getElementById('boardForm').submit();
}

function editBoard(bno) {
	location.href = '/board/' + bno + '/edit';
}

function deleteBoard(bno) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/board/' + bno + '/delete';
	}
}

const username = '${username}';
const isAuthenticated = '${isAuthenticated}'; // 로그인 여부 확인

window.addEventListener('DOMContentLoaded', () => {
	const noticeLabel = document.querySelector('#noticeLabel');
	const noticeInput = document.querySelector('#noticeCheckbox');
	
	if (!isAuthenticated && username && username !== 'admin') { // 로그인하지 않은 경우 또는 일반 사용자인 경우, 공지사항 선택금지 및 체크 해제
	    if (noticeInput !== null) {
	      noticeInput.checked = false;
	      noticeInput.disabled = true;	//비활성화안되요...ㅠㅠ
	    }
	    return;
	} else if(isAuthenticated && !username && username === 'admin'){
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
	}

});
</script>
</html>
