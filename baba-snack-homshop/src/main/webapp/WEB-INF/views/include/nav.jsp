<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var isLogged = true; // 로그인 여부 확인하는 변수, 로그인 상태로 가정
        var isAdmin = true; // 관리자 여부 확인하는 변수, 관리자 상태로 가정
        
		if (isLogged) {
			$("#login").html('<a href="/member/logout">로그아웃</a>');
			if (isAdmin) {
                $("#join").html('<a href="/admin">관리</a>');
            } else {
                $("#join").html('<a href="/member/mypage">마이페이지</a>');
				$("#nav-notMember tr").append('<td><a href="/cart/orderdetails-list">장바구니</a></td>');
            }
		} else {
			$("#login").html('<a href="/member/login">로그인</a>');
			$("#join").html('<a href="/member/join">회원가입</a>');
			$("#nav-notMember tr td:nth-child(5)").remove();
        }
        
        if (isAdmin && !isLogged) {
            $("#nav-notMamber td:first-child").remove();
            $("#nav-notMamber td:nth-child(2)").remove();
            $("#nav-notMamber td:last-child a").attr("href", "/admin");
        }
    });
</script>
<style>
#nav {
	width: 500px;
	height: 30px;
	position: relative;
	left: 850px;
	bottom: 380px;
}

#nav-notMamber td {
	padding-left: 15px;
	padding-right: 15px;
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
		<table id="nav-notMamber">
			<tr>
				<td><a href="/member/login">로그인</a></td>
				<td><a href="/member/join">회원가입</a></td>
				<td><a href="/product?category=dog">강아지간식</a></td>
				<td><a href="/product?category=cat">고양이간식</a></td>
				<td><a href="/board/board-list">게시판</a></td>
			</tr>
		</table>
	</div>
</body>
</html>