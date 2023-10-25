<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/product-css/product-write.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="/css/summernote/summernote-lite.min.css" >
<script src="/js/summernote/summernote-lite.min.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
<title>상품등록</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '<c:out value="${msg}" />';
	if (msg !== '')
		alert(msg);
</script>
<script>
//대표 사진 미리보기 함수
function previewImage(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#preview').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}


jQuery(document).ready(function($) {	
  // 메인화면 페이지 로드 함수
  $('#summernote').summernote({
    placeholder: '상품설명을 작성하세요',
    height: 400,
    maxHeight: 400
  });

  // 대표 사진 파일 선택 시 미리보기 처리
  $('#FormControlInput-photo').change(function() {
    previewImage(this);
  });

   // 저장 버튼 클릭 시 동작
   $('#submitBtn').on('click', function(e) {
     e.preventDefault(); // 기본 동작(폼 제출) 막기

     // 상품명과 내용 입력 여부 확인
     var productName = $('#FormControlInput-title').val();
     var productNotice = $('#summernote').val();
     var category = $('#FormControlInput-category').val();
     var productPhoto = $('#FormControlInput-photo').prop('files')[0];
     var productStock = $('#FormControlInput-stock').val();
     var productPrice = $('#FormControlInput-price').val();
     var productSize = $('#FormControlInput-size').val();

     if (productName.trim() === '') { // 제목이 비어있는 경우
       alert('상품명을 입력해주세요.');
       $('#FormControlInput-title').focus(); // 커서를 제목 필드로 이동
       return;
     }

     if (productNotice.trim() === '') { // 내용이 비어있는 경우
       alert('내용을 입력해주세요.');
       $('#summernote').focus(); // 커서를 내용 필드로 이동
       return;
     }

     // 폼 데이터 생성 및 전송 준비 
     var formData = new FormData();
     formData.append("title", productName);
     formData.append("category", category);
     formData.append("mainPhoto", productPhoto);
     formData.append("stock", productStock);
     formData.append("price", productPrice);
     formData.append("size", productSize);
     formData.append("notice", productNotice);
     
     // 폼 데이터 전송하기 
     $.ajax({
       url: "/product-edit/${product.pno}",   //<-- 수정된 부분: 상품의 pno 값을 전달
       type: "POST",
       data: formData,
       processData: false,
       contentType: false,
       success: function(response) {
         console.log(response); 	// 성공적으로 처리된 후의 동작 정의
         alert("${product.productName}" + " 상품이 수정되었습니다");
         location.href = "/product/admin-product";	// 상품 목록 페이지로 리다이렉트 
       },
       error: function(xhr, status, error) {
         console.error(error);  	// 에러 발생 시의 동작 정의 
         alert("수정에 실패했습니다. 다시 시도해주세요.");
       }
     });
   });
   
   // 상품 삭제 함수
   $("#deleteBtn").on('click', function(e) {
     if (confirm('정말로 상품을 삭제하시겠습니까?')) {
       $.ajax({
         url: '/product-delete/${product.pno}', // 삭제할 상품의 pno 값을 전달
         type: 'POST',
         success: function(response) {
           console.log(response); // 성공적으로 처리된 후의 동작 정의
           alert('상품이 삭제되었습니다.');
           location.href = '/product/admin-product'; // 상품 목록 페이지로 리다이렉트
         },
         error: function(xhr, status, error) {
           console.error(error); // 에러 발생 시의 동작 정의
           alert('상품 삭제에 실패했습니다. 다시 시도해주세요.');
         }
       });
     }
   });
   
   // 서버로부터 카테고리 목록 가져오기
   $.ajax({
     url: "/categories", // 카테고리 목록을 가져올 API 엔드포인트
     type: "GET",
     success: function(response) {
       // 카테고리 목록을 받아와서 옵션으로 추가
       var categories = response.categories;
       var selectElement = $('#FormControlInput-category');
       categories.forEach(function(category) {
         var option = $('<option>').val(category.value).text(category.name);
         selectElement.append(option);
       });
     },
     error: function(xhr, status, error) {
       console.error(error); // 에러 발생 시의 동작 정의
       alert("카테고리 목록을 가져오는데 실패했습니다.");
     }
   });

   // 대표 사진 URL 가져오기
   var productPhotoURL = "${product.productPhotoURL}";
   if (productPhotoURL) {
     $('#preview').attr('src', productPhotoURL);
   }
 });
</script>
</head>
<body>
	<div id="page">
		<header>
			<!-- 공지, 로고 -->
			<jsp:include page="/WEB-INF/views/include/header.jsp" />
		</header>
		<nav>
			<!-- 메뉴 -->
			<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		</nav>
		<main id="product-write-css">
			<aside>
			</aside>
			<section>
				<form id="productForm" action="/product/product-edit/${product.pno}" method="POST"> <!-- 수정된 부분: 수정할 상품의 pno 값을 전달 -->
					<div class="form-group">
						<label for="FormControlInput-title" style="margin: 10px; color: darkgray;">[상품명]</label>
						<input type="text" class="form-control" id="FormControlInput-title"
							name="title" placeholder="상품명을 작성해주세요." value="${product.productName}">
					</div>
					<div class="form-group">
						<!-- 대표 상품사진 -->
						<label for='FormControlInput-photo'	style='margin: 10px; color: darkgray;'>[상품 대표사진]</label>
						<!-- 파일 선택 시 미리보기할 이미지 영역 -->
						<img id='preview' src='' alt=''	style='max-width: 500px; max-height: 300px; border: 1px dotted black;' />
						<!-- 파일 선택 필드 -->
						<input type='file' class='form-control-file' id='FormControlInput-photo' name='mainPhoto'>
					
						<table id="plusAdd"><!-- 카테고리, 재고, 가격, 용량 -->
							<tr>
								<td>
									<select name='category' id='FormControlInput-category' class='form-control'>
										<option value='' selected disabled>카테고리선택</option>
    									<option value='CAT'>강아지</option>
   										<option value='DOG'>고양이</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									<input type="number" class="form-control" id="FormControlInput-stock" name="stock" placeholder="재고/입고량" value="${product.productStock}">
									<select class="form-select input-group-text" id="FormControlInput-stock-unit">
										<option value="">단위</option>
										<option value="개">개</option>
										<option value="묶음">묶음</option>
										<option value="통">통</option>
										<option value="병">병</option>
										<option value="봉">봉</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>
									<input type="number" class="form-control" id="FormControlInput-price" name="price" placeholder="상품가격" value="${product.productPrice}">
									<select class="form-select input-group-text" id="FormControlInput-price-unit">
										<option value="">단위</option>
										<option value="원">원</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>
									<input type="number" class="form-control" id="FormControlInput-size" name="size" placeholder="상품용량" value="${product.productSize}">
									<select class ="form-select input-group-text" id="FormControlInput-size-unit">
									<option value=""> 단위 </option >
									<option value= “g”>G</option >
									<option value= “kg”>KG</option >
									<option value= “mL”>ML </option >
									<option value= “L”>L </option >
									</select> 
								</td>
							</tr>
						</table>
					</div>
					<div class="form-group">
						<div id="summernote"></div>
					</div>
					<div class="text-right">
						<button type="submit" class="btn btn-outline-info" id="submitBtn">저장</button>
            			<button type="button" class="btn btn-outline-danger" id="deleteBtn">삭제</button>
					</div>
				</form>
			</section>
			<aside>
			</aside>
		</main>
		<footer>
			<!-- 홈피정보 -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</footer>
	</div>
</body>
</html>
