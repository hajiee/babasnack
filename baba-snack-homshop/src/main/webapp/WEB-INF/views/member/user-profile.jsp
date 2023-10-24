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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<title>BABA-SNACK-User-Profile</title>

<script>
$(document).ready(function() {
    $('#change-email').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const email = $('#email').val();
        if (email === '') {
            alert('이메일을 입력하세요.');
            return;
        }
        
        $('#hidden-email').val(email); // hidden-email 필드에 값을 설정
        $('form[action="/member/psChangeEm"]').submit(); // 이메일 변경 폼 제출
    });
    
    $('#quit').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const choice = confirm('정말 탈퇴하시겠습니까?');
        if (choice === false)
            return;
        
        $('form[action="/member/${username}"]').submit(); // 회원 탈퇴 폼 제출
    });
});
</script>

</head>

<body>

<!-- 이메일 변경 -->
<div class="container">
  <h1>이메일 변경</h1>
  <div class="">
    <label for="">새 이메일:</label>
    <input type="email" id ="email"/>
  </div>
  <button id ="change-email">변경하기</button> 
</div>

<!-- 회원 탈퇴 -->
<div class="">
  <h1>회원 탈퇴</h1> 
  <p>탈퇴 시 모든 데이터가 삭제됩니다.</p> 
  <button id ="quit">탈퇴하기</button> 
</div>


<form action="/member/psChangeEm" method ="post"> <!-- POST 요청으로 변경 -->
  	<input type ="hidden"name= "email"id= "hidden-email"/> <!-- 숨겨진 입력 필드 추가 -->
  	<input type ="submit"id= "hidden-submit"/> <!-- 숨겨진 submit 버튼 추가 -->
</form>


<form action="/member/${username}" method= "post"> <!-- POST 요청으로 변경 -->
  	<input type= "hidden"name= "_method"value= "delete"/> <!-- 숨겨진 HTTP 메서드 필드 추가 (DELETE)-->
  	<input type = "submit"id = "hidden-delete"/> <!-- 숨겨진 submit 버튼 추가 -->
</form>


<script>
$('#change-email').on("click", function() {
	  const email = $('#email').val();
	  if (email === '') {
	    alert('이메일을 입력하세요.');
	    return;
	  }
	  
	  $('#hidden-email').val(email); // hidden-email 필드에 값을 설정
	  $('#hidden-submit').click(); // hidden-submit 버튼 클릭하여 폼 제출
});

$('#quit').on("click", function() {
	  const choice = confirm('정말 탈퇴하시겠습니까?');
	  if (choice === false)
	    return;
	  
	  $('#hidden-delete').click(); // hidden-delete 버튼 클릭하여 폼 제출
});
<script>
$('#change-email').on("click", function() {
	  const email = $('#email').val();
	  if (email === '') {
	    alert('이메일을 입력하세요.');
	    return;
	  }
	  
	  $('#hidden-email').val(email); // hidden-email 필드에 값을 설정
	  $('#hidden-submit').click(); // hidden-submit 버튼 클릭하여 폼 제출
});

$('#quit').on("click", function() {
	  const choice = confirm('정말 탈퇴하시겠습니까?');
	  if (choice === false)
	    return;
	  
	  $('#hidden-delete').click(); // hidden-delete 버튼 클릭하여 폼 제출
});
</script>
	
</body>
</html>

