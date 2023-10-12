<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK-board</title>
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
		<main id="board-main">
			<aside>
			</aside>
			<section>
				<table class="table table-hover" id="board-table">
					<thead>
						<tr>
							<th class="board-nav">번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="board">
							<tr>
								<td class="board-nav">${board.bno}</td>
								<td><a href="/read?bno=${board.bno}">${board.title}</a></td>
								<td>${board.boardWriter}</td>
								<td>${board.boardDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pagination">
					<c:if test="${page.prev>0}">
						<li class="page-item"><a class="page-link"
							href="/list?pageno=${page.prev}">이전</a></li>
					</c:if>
					<c:forEach begin="${page.start}" end="${page.end}" var="i">
						<c:if test="${page.pageno==i }">
							<li class="page-item active"><a class="page-link"
								href="/list?pageno=${i}">${i}</a></li>
						</c:if>
						<c:if test="${page.pageno!=i }">
							<li class="page-item"><a class="page-link"
								href="/list?pageno=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${page.next>0}">
						<li class="page-item"><a class="page-link"
							href="/list?pageno=${page.next}">다음</a></li>
					</c:if>
				</ul>
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
