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
<style>
    .container-wrapper {
        border: 1px solid brown; /* 테두리 추가 */
        padding: 20px; /* 내부 여백 설정 (옵션) */
    }

    .petphoto {
        text-align: center;
        margin-bottom: 200px;
        margin-top: 400px;
    }

    .container {
        text-align: center;
        margin-bottom: 200px;
    }

    .pswirhlawl {
        text-align: center;
        margin-bottom: 200px;
    }
</style>

<script>
$(document).ready(function() {
    $('#register-petname').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const petName = $('#petname').val();
        if (petName === '') {
            alert('반려동물 이름을 입력하세요.');
            return;
        }
        
        alert('반려동물 이름이 등록되었습니다.');
    });

    $('#change-email').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const email = $('#email').val();
        if (email === '') {
            alert('이메일을 입력하세요.');
            return;
        }
        
        $('#hidden-email').val(email); // hidden-email 필드에 값을 설정
        $('form[action="/member/change-email"]').submit(); // 이메일 변경 폼 제출
    });

    $('#quit').on("click", function(e) {
        e.preventDefault(); // 기본 동작 취소
        
        const choice = confirm('정말 탈퇴하시겠습니까?');
        if (choice === false) {
            return;
        }
        
        $('form[action="/member/withdrawal"]').submit(); // 회원 탈퇴 폼 제출
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

<div class="container-wrapper">
    <div class="petphoto">
        <h1>반려동물 사진 업로드</h1>
        <p>사진은 한 장만 업로드 가능합니다.</p>
        <input type="file" id="petphoto-input" style="display: none;">
        <button id="photo">사진 업로드</button>
    </div>
     
    <!-- 반려동물 이름 등록 -->
    <div class="container">
        <h1>반려동물 이름 등록</h1>
        <label for="petname">반려동물 이름:</label>
        <input type="text" id="petname" name="petname" required>
        <!-- 반려동물 이름 등록 버튼 클릭 시 동작하는 함수 호출 -->
        <button id="register-petname">등록하기</button> 
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
</div>

<form action="/member/user-profile" method="post"> <!-- POST 요청으로 변경 -->
     <input type="submit" value="회원 탈퇴">
    <button id="withdrawal">회원 탈퇴</button>
</form>

</body>
</html>
