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
	width: 740px;
	height: 30px;
	position: relative;
	left: 860px;
	bottom: 380px;
}

#nav a {
  border-right: 1px solid black;
  color: #000;
  text-decoration: none;
  margin: 0px;
  margin-top:5px;
  min-width:90px;
  text-align: center;
  transition: color 0.3s ease-in-out;
}
#nav a:last-child {
  border-right: none;
  padding-right:0px;
}

#nav a:hover {
  font-weight: bold;
}

#nav h5{
	margin: auto 20px;
	font-weight: bold;
	width: 100px;
}

</style>
</head>
<body>
<!-- <form action="/member/logout" method="post">
	<button>logout</button>
</form> -->
	<div>
		<!-- 인증(로그인)이 됐는지 아닌지 확인 -->
			<!-- ADMIN 권한이 있는 경우만 "관리" 메뉴 표시 -->
			<sec:authorize access="hasRole('ADMIN')">
				<div id="nav" style="display: flex;">
					<h5>관리자계정</h5>
					 <a href="/member/logout">로그아웃</a> 
					 <a href="/admin">관리</a> 
					 <a href="/product?category=DOG">강아지</a> 
					 <a href="/product?category=CAT">고양이</a> 
					 <a href="/board/board-list">게시판</a>
				</div>
			</sec:authorize>
			<!-- 일반 사용자인 경우 "내 정보" 메뉴 표시 -->
			<sec:authorize access="hasRole('USER')">
				<div id="nav" style="display: flex; left: 750px;">
					<h5>
						<sec:authentication property="principal" var="principal"/>
						${principal.username}님
					</h5>
					 <a href="/member/logout">로그아웃</a> 
					 <a href="/member/user-profile" style="width:110px;">마이페이지</a> 
					 <a href="/product?category=DOG">강아지</a> 
					 <a href="/product?category=CAT">고양이</a> 
					 <a href="/board/board-list">게시판</a> 
					 <a href="/cart/orderdetails-list">장바구니</a>
				</div>
			</sec:authorize>
			<!-- 비인증 상태일 때 로그인 링크 표시 -->
			<sec:authorize access="isAnonymous()">
				<div id="nav" style="display: flex;">
					<h5>비회원</h5>
					 <a href="/member/login">로그인</a> 
					 <a href="/member/join">회원가입</a> 
					 <a href="/product?category=DOG">강아지</a> 
					 <a href="/product?category=CAT">고양이</a> 
					 <a href="/board/board-list">게시판</a>
				</div>
			</sec:authorize>
		</div>
</body>
</html>