<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BABA-SNACK-User-Profile</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <style>
        .container-wrapper {
            border: 1px solid brown;
            padding: 20px;
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
                e.preventDefault();
                const petName = $('#petname').val();
                if (petName === '') {
                    alert('반려동물 이름을 입력하세요.');
                    return;
                }
                alert('반려동물 이름이 등록되었습니다.');
            });

            $('#change-email').on("click", function(e) {
                e.preventDefault();
                const email = $('#email').val();
                if (email === '') {
                    alert('이메일을 입력하세요.');
                    return;
                }
                $('#hidden-email').val(email);
                $('form[action="/member/change-email"]').submit();
            });

            $('#quit').on("click", function(e) {
                e.preventDefault();
                const choice = confirm('정말 탈퇴하시겠습니까?');
                if (choice === false) {
                    return;
                }
                $('form[action="/member/withdrawal"]').submit();
            });

            $('#photo').on("click", function(e) {
                e.preventDefault();
                const fileInput = $('#petphoto-input');
                fileInput.click();
            });

            $('#petphoto-input').on("change", function() {
                const file = $(this)[0].files[0];
                if (file) {
                    $('#photo').text(file.name);
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

        <div class="container">
            <h1>반려동물 이름 등록</h1>
            <label for="petname">반려동물 이름:</label>
            <input type="text" id="petname" name="petname" required>
            <button id="register-petname">등록하기</button> 
        </div>

        <div class="container">
            <h1>이메일 변경</h1>
            <label for="email">새 이메일:</label>
            <input type="email" id="email" name="email" required>
            <button id="change-email">변경하기</button> 
        </div>

        <div class="container">
    <h1>회원 탈퇴</h1>
    <p>탈퇴 시 모든 데이터가 삭제됩니다.</p>
    <form action="/member/withdrawal" method="post">
        <button type="submit">회원 탈퇴</button>
    </form>
</div>
    </div>
</body>
</html>
