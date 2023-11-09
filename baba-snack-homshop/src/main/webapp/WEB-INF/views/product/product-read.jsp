<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board-css/board.css">
<link rel="stylesheet" href="/css/product-css/product-read.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>상품상세정보</title>
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
//리뷰 등록 버튼 클릭 이벤트 핸들러
//리뷰 등록 버튼 클릭 이벤트 핸들러
function submitReview(role) {
  var formData;
  var formId;

  if (role === 'buyer') {
    formData = new FormData($('#review-form-buyer')[0]);
    formId = '#review-form-buyer';
  } else if (role === 'admin') {
    formData = new FormData($('#review-form-admin')[0]);
    formId = '#review-form-admin';
  }

  $.ajax({
    url: '/product/product-read?pno=${product.pno}',
    type: 'POST', // 요청 메소드를 POST로 변경
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
      var newReview = "<li>새로운 리뷰 내용</li>";
      $("#review-list").prepend(newReview); // 새로운 리뷰를 목록 상단에 추가

      // 폼 초기화
      $(formId)[0].reset();
      $(formId).find('.star').removeClass('filled');
      $(formId).find('#rating').val(0);
      $(formId).find('#rating-value').text(0);
      $(formId).find('#review-preview').attr('src', '');
    },
    error: function(xhr, status, error) {
      console.error(error); // 에러 발생 시의 동작 정의
      alert('리뷰 등록 중 에러가 발생했습니다. 다시 작성해주세요.');
    },
    statusCode: {
      405: function() {
        console.error('Method Not Allowed'); // 405 에러 처리
        alert('서버에서 해당 요청 방법을 허용하지 않습니다.');
      }
    }
  });
}

$(document).ready(function() {
	// 파일 선택 시 미리보기 함수
	  function readURL(input) {
	    if (input.files && input.files[0]) {
	      var reader = new FileReader();

	      reader.onload = function(e) {
	        $('#review-preview').attr('src', e.target.result);
	      }

	      reader.readAsDataURL(input.files[0]);
	    }
	  }

	  // 파일 선택 시 미리보기 이벤트 처리
	  $(document).on('change', 'input[type="file"]', function() {
	    readURL(this);
	  });
	
	// 빈별 클릭 이벤트 처리
	$(document).on("click", ".star", function() {
        var ratingValue = $(this).data("rating");
        
        // 모든 별에 대해 'filled' 클래스 제거
        $(".star").removeClass("filled");
        
        // 클릭한 별과 그 이전의 별들에게 'filled' 클래스 추가
        $(this).addClass("filled").prevAll(".star").addClass("filled");
        
        // rating input 요소의 값을 변경
        $("#rating").val(ratingValue);
        
        // 별점(value)을 label 태그 옆에 표시
        $("#rating-value").text(ratingValue);
    });
	
	// 기본 별점 설정
    var defaultRating = 3;
    $(".star[data-rating='" + defaultRating + "']").addClass("filled").prevAll(".star").addClass("filled");
    $("#rating").val(defaultRating);
    $("#rating-value").text(defaultRating);
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
	<main id="board-main" >
		<aside>
		</aside>
		<section>
		<sec:authorize access="hasRole('ADMIN')">
			<a href="/product/admin-product">
				<button type='button' class='btn btn-outline-dark'>상품관리</button>
			</a>
		</sec:authorize>
				<div id="get-productRead-full">
					<table id="get-productRead">
						<tr>
							<th colspan='3'>
								<h2 id="productTitle">${product.productName}</h2>
							</th>
						</tr>
						<tr>
							<td rowspan='6' id="get-productPhoto-page">
								<img id="get-productPhoto" src="" alt="${product.productName} 사진">
							</td>
						</tr>
						<tr>
							<td>가격: ${product.productPrice}원</td>
						</tr>
						<tr>
							<td id="get-productStock">재고:</td>
						</tr>
						<tr>
							<td>용량: ${product.productSize}</td>
						</tr>
						<tr>
							<td>리뷰: ${product.reviewCount} / 평균 별점:${product.reviewStar}</td>
						</tr>
						<tr>
							<td>
								<select id="quantity" name="productCnt">
									<option value="0">구매수량선택</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</td>
							<td>
								<div class="btn-group">
								<a href="/cart/orderdetails-list">
  									<button type="button" class="btn btn-secondary">장바구니</button>
  								</a>
  									<button type="button" class="btn btn-dark">구매하기</button>
  								</div>
							</td>
						</tr>
					</table>
					<hr>
					<div id="get-productNotice-full">${product.productNotice}</div>
					<hr>
					<div id="reviewPage">
					  <sec:authorize access="hasRole('ROLE_BUYER')">
						 <form id="review-form-buyer" enctype="multipart/form-data">
							<input type="text" name="reviewWrite" placeholder="작성자" value="${username}" disabled>
							<label for="rating">별점 : [ <span id="rating-value"></span> ]</label>
							<div id="rating-stars">
        						<i class="star far fa-star" data-rating="1"></i>
    							<i class="star far fa-star" data-rating="2"></i>
    							<i class="star far fa-star" data-rating="3"></i>
    							<i class="star far fa-star" data-rating="4"></i>
    							<i class="star far fa-star" data-rating="5"></i>
    						</div>
    						<input type="hidden" id="rating" name="star" value="0" required>
							<!-- 파일 선택 시 미리보기할 이미지 영역 -->
							<img id='review-preview' src='' alt=''/>
							<!-- 파일 선택 필드 -->
							<input type="file" class="form-control-file" name="reviewPhoto" multiple="multiple"><br>
							<textarea id="add-reviewNotice" name="reviewNotice" placeholder="리뷰 내용" required></textarea>
							<button type="button" class="btn btn-outline-warning" onclick="submitReview('buyer')">리뷰 등록</button>
						</form>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<form id="review-form-admin" enctype="multipart/form-data">
							<input type="text" name="reviewWrite" placeholder="작성자" required>
    						<label for="rating">별점 : [ <span id="rating-value"></span> ]</label>
							<div id="rating-stars">
        						<i class="star far fa-star" data-rating="1"></i>
    							<i class="star far fa-star" data-rating="2"></i>
    							<i class="star far fa-star" data-rating="3"></i>
    							<i class="star far fa-star" data-rating="4"></i>
    							<i class="star far fa-star" data-rating="5"></i>
    						</div>
    						<input type="hidden" id="rating" name="star" value="0" required>
							<!-- 파일 선택 시 미리보기할 이미지 영역 -->
							<img id='review-preview' src='' alt=''/>
							<!-- 파일 선택 필드 -->
							<input type="file" class="form-control-file" name="reviewPhoto" multiple="multiple"><br>
							<textarea id="add-reviewNotice" name="reviewNotice" placeholder="리뷰 내용" required></textarea>
							<button type="button" class="btn btn-outline-warning" onclick="submitReview('admin')">리뷰 등록</button>
						</form>
						<form id="review-form-admin-reply" action="/product/product-read?pno=${product.pno}" method="post" enctype="multipart/form-data">
							<input type="text" name="reviewWrite" value="관리자" disabled>
        					<textarea id="add-reviewNotice" name="reviewNotice" placeholder="댓댓글 내용" required></textarea>
        					<button type="submit"  class="btn btn-outline-dark">관리자리뷰 작성</button>
						</form>
						</sec:authorize>
						<div id="reviewPage-list">
							<h3>----- 리뷰 ------------------------------------------------------------------------------------------------------------------------------</h3>
							<ul id="review-list">
								<c:forEach items="${reviews}" var="review">
									<li>
										<div class="review-info">
											<span class="review-writer">${review.reviewWrite}</span>
											<span class="review-date">${review.reviewDate}</span>
											<div class="review-star">
												<c:forEach begin="1" end="${review.star}" varStatus="starIndex">
													<i class="fas fa-star"></i>
												</c:forEach>
											</div>
										</div>
										<p class="review-notice">${review.reviewNotice}</p>
										<c:if test="${not empty review.reviewPhoto}">
											<div class="review-photos">
												<c:forEach items="${review.reviewPhoto}" var="photo">
													<img src="${photo.reviewSaveImg}" alt="${photo.reviewImg}">
												</c:forEach>
											</div>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
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