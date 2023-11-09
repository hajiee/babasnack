<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>메인/상품검색</title>
<script>
$(document).ready(function() {
    $("#search-btn").click(function(event) {
        event.preventDefault();
        
        var searchInput = $("#search-input").val();
        
        var category = "";
        
        if (searchInput === "CAT") {
            category = "고양이";
        } else if (searchInput === "DOG") {
            category = "개";
        } else {
            console.error("유효하지 않은 카테고리:", searchInput);
            return;
        }
        
        // 검색어를 포함하여 상품 목록 페이지로 이동
        location.href = "/product/product-list?search=" + searchInput;
    });
});
</script>
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
	#search-btn{
		width:100px;
	}
</style>
<style>
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 24
}
.material-symbols-outlined{
	padding:8px;
	position: relative;
	left:250px
}
</style>
</head>
<body>
	<div id="logo" class="col s12">
		<a href="/"><img src="/images/00로고.png" style="height:245px" alt="멍냥이 간식쇼핑몰"></a>
	</div>
	<form id="search" class="d-flex" action="/product/product-list">
		<span class="material-symbols-outlined"> search </span>
		<input id="search-input" class="form-control me-2" type="text" name="search" placeholder="상품검색창">
		<button id="search-btn" class="btn btn-primary" type="button">찾기</button>
	</form>
</body>
</html>