<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#nav{
		width: 500px;
		height: 30px;
		position: relative;
		left: 850px;
		bottom: 380px;
	}
	#nav-notMamber td{
		padding-left: 15px;
		padding-right: 15px;
		border-collapse: collapse; /* 셀 사이 간격 제거 */
	}
	#nav-notMamber td:not(:last-child){
  		border-right: 1px solid black; /* 가운데 선 스타일 설정 */
	}
	#nav-notMamber td>a{
		color: #000;
		text-decoration: none;
		font-size: 15px; /* 원래 글자 크기 */
  		transition: font-size 0.3s; /* 글자 크기 변경에 애니메이션 효과 추가 */	
	}
	#nav-notMamber td>a:hover{
  		font-size: 18px; /* 마우스 커서가 요소 위에 있을 때 글자 크기 증가 */
	}
</style>
</head>
<body>
	<div id="nav">
		<table id="nav-notMamber">
			<tr>
				<td><a href="/member/login">로그인</a></td>
				<td><a href="/member/join">회원가입</a></td>
				<td><a href="/product/product-dog">강아지간식</a></td>
				<td><a href="/product/product-cat">고양이간식</a></td>
				<td><a href="/board/board-list">게시판</a></td>
			</tr>
		</table>
	</div>
</body>
</html>