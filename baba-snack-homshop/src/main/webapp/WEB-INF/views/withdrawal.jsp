<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
</head>
<body>
    <h1>회원 탈퇴</h1>
    <p>회원 탈퇴가 완료되었습니다.</p>
    <script>
        setTimeout(function() {
            window.location.href = "/main"; // 탈퇴 완료 후 메인 페이지로 리다이렉트
        }, 3000); // 3초 후에 리다이렉트
    </script>
</body>
</html>