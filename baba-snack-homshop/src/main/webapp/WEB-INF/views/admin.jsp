<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/admin.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK-AdminPage</title>
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
		<header> <!-- 공지, 로고 -->
			<jsp:include page="/WEB-INF/views/include/header.jsp" />
		</header>
		<nav> <!-- 메뉴 -->
			<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		</nav>
		<main id="admin-main">
			<aside>
			</aside>
			<section>
				<div class="adminMember">
					<a href="/member/admin-member">
						<button type="button" class="btn btn-warning btn-lg">회원관리</button>
					</a>
				</div>
				<div id="adminProduct">
					<a href="/product/admin-product">
						<button type="button" class="btn btn-warning btn-lg">상품관리</button>
					</a>
				</div>
				<div id="adminBoard">
					<a href="/board/admin-board">
						<button type="button" class="btn btn-warning btn-lg">게시판관리</button>
					</a>
				</div>
			</section>
			<aside>
			</aside>
		</main>
		<footer>
		</footer>
	</div>
</body>
</html>
