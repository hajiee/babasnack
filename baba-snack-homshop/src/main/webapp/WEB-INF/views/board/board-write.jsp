<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    
	// 관리자인 경우 공지사항 체크박스 기본 선택 설정
	const username = '${username}';
	if (username === 'admin') {
		document.getElementById('noticeCheckbox').checked = true;
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
			<aside>
			</aside>
			<section>
				<form action='<c:url value='/board/boardInsert'/>' method="post">
					<div class="form-group">
						<label for="exampleFormControlInput1">제목</label> <input
							type="text" class="form-control" id="exampleFormControlInput1"
							name="title" placeholder="제목을 작성해주세요.">
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1">내용</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="contents" rows="10"></textarea>
					</div>
					<button type= 'button' class= 'btn btn-info' onclick= "submitForm('/board/board-write')">등록</button>
					<c:forEach var="board" items="${boardes}">
						<div class="board-item">
							<h3>${board.title}</h3>
							<p>${board.contents}</p>

							<!-- 수정 버튼 -->
							<button type="button" class="btn btn-info" onclick="editBoard(${board.bno})">수정</button>

							<!-- 삭제 버튼 -->
							<button type="button" class="btn btn-danger" onclick="deleteBoard(${board.bno})">삭제</button>
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
</html>
