<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            margin-bottom: 20px;
        }

        .custom-file-upload {
            display: inline-block;
            padding: 6px 12px;
            cursor: pointer;
            background-color: #428bca;
            color: #fff;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .custom-file-upload:hover {
            background-color: #3071a9;
        }

        .custom-file-upload i {
            margin-right: 5px;
        }

        #petphoto-input {
            display: none;
        }

        #upload-photo {
            margin-top: 10px;
            padding: 6px 12px;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        #upload-photo:hover {
            background-color: #449d44;
        }

        .container {
            text-align: center;
            margin-bottom: 200px;
        }
    </style>
</head>
<body>
    <div class="container-wrapper">
        <div class="petphoto">
            <h1>반려동물 사진 업로드</h1>
            <p>사진은 한 장만 업로드 가능합니다.</p>
            <input type="file" id="petphoto-input" style="display: none;">
            <label for="petphoto-input" class="custom-file-upload">
                <i class="fas fa-cloud-upload-alt"></i> 사진 선택
            </label>
            <span id="selected-photo"></span>
            <button id="upload-photo">업로드</button>
            <br>
            <img id="uploaded-photo" src="" alt="업로드된 사진">
        </div>

        <div class="container">
            <h1>반려동물 등록</h1>
            <label for="petname">반려동물 이름:</label>
            <input type="text" id="petname" name="petname" required>

            <label for="pettype">반려동물 종류:</label>
            <select id="pettype" name="pettype" required>
                <option value="">종류 선택</option>
                <option value="개">개</option>
                <option value="고양이">고양이</option>
            </select>

            <label for="petgender">성별:</label>
            <select id="petgender" name="petgender" required>
                <option value="">성별 선택</option>
                <option value="암컷">암컷</option>
                <option value="수컷">수컷</option>
            </select>

            <button id="register-pet">등록하기</button>
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

        <script>
            $(document).ready(function() {
                $('#register-pet').on("click", function(e) {
                    e.preventDefault();
                    const petName = $('#petname').val();
                    const petType = $('#pettype').val();
                    const petGender = $('#petgender').val();

                    if (petName === '') {
                        alert('반려동물 이름을 입력하세요.');
                        return;
                    }

                    if (petType === '') {
                        alert('반려동물 종류를 선택하세요.');
                        return;
                    }

                    if (petGender === '') {
                        alert('성별을 선택하세요.');
                        return;
                    }

                    // 선택한 정보를 서버로 제출하거나 필요한 로직을 수행할 수 있습니다.
                    // 이 예제에서는 간단히 선택한 정보를 콘솔에 출력합니다.
                    console.log("반려동물 이름:", petName);
                    console.log("반려동물 종류:", petType);
                    console.log("성별:", petGender);

                    alert('반려동물이 등록되었습니다.');
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
                        $('#selected-photo').text(file.name);
                    }
                });

                $('#upload-photo').on("click", function(e) {
                    e.preventDefault();
                    const fileInput = $('#petphoto-input');
                    const file = fileInput[0].files[0];
                    if (!file) {
                        alert('파일을 선택하세요.');
                        return;
                    }
                    const formData = new FormData();
                    formData.append('file', file);
                    $.ajax({
                        url: '/member/uploadPetPhoto',
                        type: 'POST',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function(response) {
                            // 업로드 성공 시 동작
                            alert('사진이 업로드되었습니다.');
                            // 업로드된 사진 경로를 받아와서 화면에 표시
                            const photoUrl = response.photoUrl;
                            $('#uploaded-photo').attr('src', photoUrl);
                        },
                        error: function(xhr, status, error) {
                            // 업로드 실패 시 동작
                            alert('사진 업로드에 실패했습니다.');
                        }
                    });
                });
            });
        </script>
    </div>
</body>
