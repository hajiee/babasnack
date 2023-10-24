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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK-User-Profile</title>
<script>
$(document).ready(function() {
    $('#change-email').on("click", function() {
        const email = $('#email').val();
        if (email === '') {
            alert('이메일을 입력하세요.');
            return;
        }
        
        const form = $('<form>', {
            action: '/member/psChangeEm',
            method: 'post'
        });
        
        $('<input>').attr({
            type: 'hidden',
            name: 'email',
            value: email
        }).appendTo(form);
        
        form.appendTo($('body')).submit();
    });
});
$('#quit').on("click", function() {
    const choice = confirm('정말 탈퇴하시겠습니까?');
    if (choice === false)
        return;
    
    const form = $('<form>', {
        action: '/member/' + username,
        method: 'post'
    });
    
    form.appendTo($('body')).submit();
});
  });
</script>
</head>
<body>
	
</body>
</html>