<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK</title>
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
			<p>나 메뉴</p>
		</nav>
		<main>
			<aside id="best"> <!-- 베스트상품 -->
				<p>나 베스트</p>
			</aside>
			<section> <!-- 메인 상품 -->
				<div class="category-product">
					<a href="/product/product-dog"><img scr="C:\Users\user\Downloads\712353f8-0a3c-422d-a311-e06e58d53160.png">강아지 상품페이지</a>
				</div>
				<div class="category-product">
					<a href="/product/product-cat"><img scr="C:\Users\user\Downloads\16e80cf6-531b-48b0-b8df-8068a6c06be2.png">고양이 상품페이지</a>
				</div>
			</section>
			<div id="ad">	
				<aside> <!-- 광고 -->
					<jsp:include page="/WEB-INF/views/include/aside.jsp" />
				</aside>
			</div>
		</main>
			
		<footer> <!-- 홈피정보 -->
			<p>나 정보</p>
		</footer>
	</div>
</body>
</html>
