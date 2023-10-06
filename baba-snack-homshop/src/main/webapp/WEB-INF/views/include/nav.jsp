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
		left: 800px;
		bottom: 460px;
	}
	#nav-notMamber td{
		padding-left: 15px;
		padding-right: 15px;
		border-collapse: collapse; /* 셀 사이 간격 제거 */
	}
	#nav-notMamber td:not(:last-child){
  		border-right: 1px solid black; /* 가운데 선 스타일 설정 */
	}
</style>
</head>
<body>
	<div id="nav">
		<table id="nav-notMamber">
			<tr>
				<td>로그인</td>
				<td>회원가입</td>
				<td>강아지간식</td>
				<td>고양이간식</td>
				<td>게시판</td>
			</tr>
		</table>
	</div>
</body>
</html>