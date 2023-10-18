<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(document).ready(function() {
    var isLogged = false; // 로그인 여부 확인하는 변수, 비로그인 상태로 가정
    var isAdmin = false; // 관리자 여부 확인하는 변수, 비관리자 상태로 가정

    function updateMenu() {
        if (isLogged && isAdmin) {  // 관리자로 로그인한 경우
            $("#login-nav").html('<a href="/member/logout">로그아웃</a>');
            $("#join-nav").html('<a href="/admin">관리</a>');
            $("#cart-nav").remove(); // 장바구니 메뉴 삭제
        } else if (isLogged && !isAdmin) {  // 일반 회원으로 로그인한 경우
            $("#login-nav").html('<a href="/member/logout">로그아웃</a>');
            $("#join-nav").html('<a href="/mypage">마이페이지</a>');
        } else {  // 비로그인 상태일 경우
            $("#login-nav").html('<a href="/member/login">로그인</a>');
            $("#join-nav").html('<a href="/member/join">회원가입</a>');
        }

        if (!isLogged && !isAdmin) {  // 비로그인 상태일 때 "로그인"으로 변경
        	$("#login-nav a").attr("href", "/member/login");
        	$("#login-nav a").text("로그인");
        }
        
        if (isLogged) {  // 현재 인증된 사용자 이름 출력
        	var username = "${principal.username}";
        	$("th#username-label").text("Welcome, " + username + "님!");
    	}
    }

    $(document).on("click", "#login-nav a", function(e) {
        e.preventDefault();

        if (isLogged || isAdmin) {   // 현재 로그아웃하기 위해 클릭한 경우 (현재는 모든 사용자가 로그아웃 처리됨)
    		isLogged = false;
        	isAdmin = false;

	       	if(window.location.pathname !== "/member/login") { 
        		window.location.href = "/member/logout";
    		} else {
    			window.location.href = "/member/login";  // 페이지 이동 추가
    		}
    	} else if(!isLogged && !isAdmin){   // 현재 비회원이며 "로그인" 링크를 클릭하여 로그인 페이지로 이동하는 경우
    		window.location.href = "/member/login";
    	}

	    if (isLogged && isAdmin) {  // 현재는 모든 사용자가 "로그아웃"으로 표시됨.
	    	$("#login-nav a").attr("href", "/member/login");
	    	$("#login-nav a").text("로그인");
	    } else {
	    	$("#login-nav a").attr("href", "/member/logout");
	    	$("#login-nav a").text("로그아웃");
	    }
	    updateMenu();  // 메뉴 업데이트 수행
    });
    updateMenu();  // 초기 메뉴 업데이트 수행
});
</script>
<style>
#nav {
	width: 580px;
	height: 30px;
	position: relative;
	left: 830px;
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
	<div id="nav">
		<!-- 인증(로그인)이 됐는지 아닌지 확인 -->
		<table id="nav-notMamber">
			<sec:authorize access="isAuthenticated()">
					<!-- ADMIN 권한이 있는 경우만 "관리" 메뉴 표시 -->
					<sec:authorize access="hasRole('ADMIN')">
					<tr>
						<td><a href="/logout">로그아웃</a></td>
						<td><a href="/Admin">관리</a></td>
						<td><a href="/product?category=dog">강아지간식</a></td>
						<td><a href="/product?category=cat">고양이간식</a></td>
						<td><a href="/board/board-list">게시판</a></td>
					</tr>
					</sec:authorize>
					<!-- 일반 사용자인 경우 "내 정보" 메뉴 표시 -->
					<sec:authorize access="hasRole('USER')">
					<tr>
						<td><a href="/logout">로그아웃</a></td>
						<td><a href="/mypage">마이페이지</a></td>
						<td><a href="/product?category=dog">강아지간식</a></td>
						<td><a href="/product?category=cat">고양이간식</a></td>
						<td><a href="/board/board-list">게시판</a></td>
						<td><a href="/cart/orderdetails-list">장바구니</a></td>
					</tr>
					</sec:authorize>
					<!-- 현재 인증된 사용자 이름 출력 -->
					<tr>
						<th>Welcome, ${principal.username}님!</th>
					</tr>
			</sec:authorize>
			<!-- 비인증 상태일 때 로그인 링크 표시 -->
			<sec:authorize access="!isAuthenticated()">
			<tr>
				<td id="login-nav"><a href="/member/login">로그인</a></td>
				<td id="join-nav"><a href="/member/join">회원가입</a></td>
				<td><a href="/product?category=dog">강아지간식</a></td>
				<td><a href="/product?category=cat">고양이간식</a></td>
				<td><a href="/board/board-list">게시판</a></td>
			</tr>
			</sec:authorize>
		</table>
	</div>
</body>
</html>