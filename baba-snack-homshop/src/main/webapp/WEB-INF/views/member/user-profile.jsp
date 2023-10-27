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
    
    $('#photo').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const fileInput = $('#petphoto-input');
        fileInput.click(); // 파일 선택 input 클릭
    });
    
    $('#petphoto-input').on("change", function() {
        const file = $(this)[0].files[0];
        if (file) {
            $('#photo').text(file.name); // 선택된 파일명 표시
        }
    });
});
</script>

</head>

<body>

<!-- 반려동물 사진 업로드 -->
<div class="petphoto">
    <h1>반려동물 사진 업로드</h1>
    <p>사진은 한 장만 업로드 가능합니다.</p>
    <input type="file" id="petphoto-input" style="display: none;">
    <button id="photo">사진 업로드</button>
</div>

<!-- 이메일 변경 -->
<div class="container">
    <h1>이메일 변경</h1>
    <label for="email">새 이메일:</label>
    <input type="email" id="email" name="email" required>
    <button id="change-email">변경하기</button> 
</div>

<!-- 회원 탈퇴 -->
<div class="pswirhlawl">
    <h1>회원 탈퇴</h1> 
    <p>탈퇴 시 모든 데이터가 삭제됩니다.</p> 
    <button id="quit">탈퇴하기</button> 
</div>


<form action="/member/psChangeEm" method="post"> <!-- POST 요청으로 변경 -->
    <input type="hidden" name="email" id="hidden-email"/> <!-- 숨겨진 입력 필드 추가 -->
    <button type="submit" id="hidden-submit" style="display: none;"></button> <!-- 숨겨진 submit 버튼 추가 -->
</form>


<form action="/member/${username}" method="post"> <!-- POST 요청으로 변경 -->
    <input type="hidden" name="_method" value="delete"/> <!-- 숨겨진 HTTP 메서드 필드 추가 (DELETE)-->
    <button type="submit" id="hidden-delete" style="display: none;"></button> <!-- 숨겨진 submit 버튼 추가 -->
</form>

<form action="/uploadPetImg" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept="image/*" id="petphoto-input" style="display: none;">
    <input type="hidden" name="petpno" value="<c:out value='${petpno}'/>"> 
    <button type="submit">Upload</button>
</form>

</body>
</html>
