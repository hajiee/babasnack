<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.babasnack.demo.member.service.MemberService" %>
<%@ page import="com.babasnack.demo.member.service.MemberResetService" %>
<%
String email = request.getParameter("email");
String username = request.getParameter("username");

MemberService memberService = new MemberService(null, null);

if (email != null && username != null) {
    MemberResetService passwordResetService = new MemberResetService(null, null);
    passwordResetService.resetPassword(email, username);
    out.println("임시 비밀번호가 이메일로 전송되었습니다.");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<h1>비밀번호 찾기</h1>
<form action="passwordReset.jsp" method="post">
    <div>
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>
    </div>
    <div>
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <button type="submit">비밀번호 찾기</button>
</form>
</body>
</html>
