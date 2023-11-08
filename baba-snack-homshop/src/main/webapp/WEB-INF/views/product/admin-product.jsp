<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board-css/board.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>BABA-SNACK-board</title>
<script>
	// 루트 경로는 에러메시지 출력을 해줘야 한다
	// 가장 단순한 방법 :  에러가 발생하면 주소 뒤에 에러 추가해서 이동 http://localhost?error
	if (location.search === "?error")
		alert("잘못된 작업입니다");

	// RedirectAttribute을 이용한 에러 메시지 처리
	const msg = '${msg}';
	if (msg !== '')
		alert(msg);
</script>
<script>
$(document).ready(function() {
	$("tbody tr").each(function() {
		var productDay = $(this).find("td:eq(3)").text();
		$(this).find("td:eq(3)").text(productDay);
	});

	var page = 2; // 초기 로딩 시에는 page 값을 2로 설정하여 추가적인 상품을 가져올 수 있도록 함
    var size = 10; // 한 번에 보여줄 상품 수
    var count = 10; // 초기 로딩 시 상품의 개수를 10으로 설정

    function loadMore() {
        $.ajax({
            url: "/product/page/" + page + "/" + size,
            method: "GET",
            success: function(response) {
                var products = response;
                if (products.length > 0) {
                    for (var i = 0; i < products.length; i++) {
                        var product = products[i];
                        var row = "<tr>" +
                            "<td class='board-no'>" + product.pno + "</td>" +
                            "<td><a href='/product/product-read?pno=" + product.pno + "'>" + product.productName + "(" + product.category + ")</a></td>" +
                            "<td>" + product.productStock + "</td>" +
                            "<td>" + product.productDay + "</td>" +
                            "<td id='editBtn'><a href='/product/product-edit?pno=" + product.pno + "'><button type='button' class='btn btn-outline-warning'>수정</button></a></td>" +
                            "<td>" + (count + i + 1) + "</td>" +
                            "</tr>";
                        $("tbody").append(row);
                    }
                    page++; // page 값을 증가시킴
                    count += products.length;
                } else {
                    $("#loadMoreBtn").hide();
                    $("tbody").append("<tr style='height: 200px;'><td colspan='6'><a href='/product/admin-product' style='text-decoration: none; color:darkgray;'>더 이상 등록된 상품이 없습니다.</a></td></tr>");
                }
            },
            error: function() {
                alert("상품을 불러오는 중에 오류가 발생했습니다.");
            }
        });
    }

    $("#loadMoreBtn").click(function() {
        loadMore();
    });
    
 	// 초기 로딩 시에 오른쪽 상단에 있는 표를 삭제
    $("#aside-board-list").empty();
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
    <main id="board-main">
        <aside id="aside-board-list">
            <a href="/product/product-write">
                <button type="button" class="btn btn-outline-warning">상품등록</button>
            </a>
        </aside>
        <section>
            <table class="table table-hover" id="board-table">
                <thead>
                    <tr>
                        <th class="board-no">상품번호</th>
                        <th>상품명(분류)</th>
                        <th>재고</th>
                        <th>등록일자</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="product" varStatus="status">
                        <tr>
                            <td class="board-no">${product.pno}</td>
                            <td><a href="/product/product-read?pno=${product.pno}">${product.productName}(${product.category})</a></td>
                            <td>${product.productStock}</td>
                            <td>${product.productDay}</td>
                            <td id="editBtn">
                                <a href="/product/product-edit?pno=${product.pno}">
                                    <button type="button" class="btn btn-outline-warning">수정</button>
                                </a>
                            </td>
                            <td>${status.index + 1}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="button" class="btn btn-outline-secondary" id="loadMoreBtn" style="width: 1520px;">더 보기</button>
        </section>
        <aside></aside>
    </main>
    <footer></footer>
</div>
</body>
</html>
