<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>
<style>
	#logo {
		width: 350px;
		height: 250px;
		margin-bottom:100px;
		text-align: center;
		position: relative;
		bottom: -100px;
		left:10px;
	}
	
	#search{
		width: 380px;
		height: 40px;
		position: relative;
		left: 800px;
		bottom: 150px;
	}
	#search>button{
		width:100px;
	}
	
</style>
</head>
<body>
	<div id="logo" class="col s12">
		<a href="/"><img src="/images/00로고.png" style="height:245px" alt="멍냥이 간식쇼핑몰"></a>
	</div>
	<form id="search" class="d-flex">
        <input class="form-control me-2" type="text" placeholder="검색할꺼임">
        <button class="btn btn-primary" type="button">찾기</button>
     </form>
</body>
</html>