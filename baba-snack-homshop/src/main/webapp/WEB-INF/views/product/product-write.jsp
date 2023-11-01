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
// 대표 사진 미리보기 함수
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

	//대표 사진 파일 선택 시 미리보기 처리
  $('#FormControlInput-productPhoto').change(function() {
    previewImage(this);
  });

  $('#submitBtn').click(function(e) {
	  e.preventDefault(); // 기본 동작(폼 제출) 막기

	  // 상품명과 내용 입력 여부 확인
	  var productName = $('#FormControlInput-productName').val();
	  var productNotice = $('#summernote').val();

	  if (productName.trim() === '') { // 제목이 비어있는 경우
	    alert('상품명을 입력해주세요.');
	    $('#FormControlInput-productName').focus(); // 커서를 제목 필드로 이동
	    return;
	  }

	  var productNotice = $('#summernote').summernote('code'); // 내용을 가져오는 부분 추가
	  if (!productNotice || productNotice.trim() === '') { // 내용이 비어있는 경우
	    alert('내용을 입력해주세요.');
	    $('#summernote').summernote('focus'); // 커서를 내용 필드로 이동
	    return;
	  }

	  // 폼 제출하기
	  $('#productForm').submit();
	});
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
				<form id="productForm" action="/product/add/" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="FormControlInput-productName" style="margin: 10px; color: darkgray;">[상품명]</label>
						<input type="text" class="form-control" id="FormControlInput-productName"
							name="productName" placeholder="상품명을 작성해주세요.">
					</div>
					<div class="form-group">
						<!-- 대표 상품사진 -->
						<label for='FormControlInput-productPhoto'	style='margin: 10px; color: darkgray;'>[상품 대표사진]</label>
						<!-- 파일 선택 시 미리보기할 이미지 영역 -->
						<img id='preview' src='' alt=''	style='max-width: 500px; max-height: 300px; border: 1px dotted black;' />
						<!-- 파일 선택 필드 -->
						<input type="file" class="form-control-file" id="FormControlInput-productPhoto" name="photos" multiple="multiple">
					
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
									<input type="number" class="form-control" id="FormControlInput-stock" name="productStock" placeholder="재고/입고량">
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
									<input type="number" class="form-control" id="FormControlInput-price" name="productPrice" placeholder="상품가격">
									<select class="form-select input-group-text" id="FormControlInput-price-unit">
										<option value="">단위</option>
										<option value="원">원</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>
									<input type="number" class="form-control" id="FormControlInput-size" name="productSize" placeholder="상품용량">
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
						<textarea id="summernote" name="productNotice"></textarea>
					</div>
					<div class="text-right">
						<button type='submit' class='btn btn-outline-info' id="submitBtn">등록</button>
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
