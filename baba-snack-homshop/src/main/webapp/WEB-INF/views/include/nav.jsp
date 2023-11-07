<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
#nav {
	width: 650px;
	height: 30px;
	position: relative;
	left: 800px;
	bottom: 380px;
}

#nav-notMamber td {
	padding-left: 12px;
	padding-right: 12px;
	border-collapse: collapse; /* 셀 사이 간격 제거 */
}

#nav-notMamber td:not(:last-child) {
	border-right: 1px solid black; /* 가운데 선 스타일 설정 */
}

#nav-notMamber td>a {
	color: #000;
	text-decoration: none;
	font-weight: normal; /* 원래 글자 두께 (일반) */
	transition: font-weight 0.3s; /* 글자 크기 변경에 애니메이션 효과 추가 */
}

#nav-notMamber td>a:hover {
	font-weight: bold; /* 마우스 커서가 요소 위에 있을 때 글자를 진하게 표시 (굵게) */
}
</style>
</head>
<body>
<!-- <form action="/member/logout" method="post">
	<button>logout</button>
</form> -->
	<div id="nav">
		<!-- 인증(로그인)이 됐는지 아닌지 확인 -->
		<table id="nav-notMamber">
			<!-- ADMIN 권한이 있는 경우만 "관리" 메뉴 표시 -->
			<sec:authorize access="hasRole('ADMIN')">
				<tr>
					<th>관리자계정</th>
					<td><a href="/member/logout">로그아웃</a></td>
					<td><a href="/admin">관리</a></td>
					<td><a href="/product?category=dog">강아지</a></td>
					<td><a href="/product?category=cat">고양이</a></td>
					<td><a href="/board/board-list">게시판</a></td>
				</tr>
			</sec:authorize>
			<!-- 일반 사용자인 경우 "내 정보" 메뉴 표시 -->
			<sec:authorize access="hasRole('USER')">
				<tr>
					<th>
						<sec:authentication property="principal" var="principal"/>
						${principal.username}님
					</th>
					<td><a href="/member/logout">로그아웃</a></td>
					<td><a href="/member/user-profile">마이페이지</a></td>
					<td><a href="/product?category=dog">강아지</a></td>
					<td><a href="/product?category=cat">고양이</a></td>
					<td><a href="/board/board-list">게시판</a></td>
					<td><a href="/cart/orderdetails-list">장바구니</a></td>
				</tr>
			</sec:authorize>
			<!-- 비인증 상태일 때 로그인 링크 표시 -->
			<sec:authorize access="isAnonymous()">
				<tr>
					<td id="login-nav"><a href="/member/login">로그인</a></td>
					<td id="join-nav"><a href="/member/join">회원가입</a></td>
					<td><a href="/product?category=dog">강아지</a></td>
					<td><a href="/product?category=cat">고양이</a></td>
					<td><a href="/board/board-list">게시판</a></td>
				</tr>
			</sec:authorize>
		</table>
	</div>
</body>
</html>