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
		var joinDay = $(this).find("td:eq(3)").text();
		$(this).find("td:eq(3)").text(joinDay);
	});

	var displayedJoinDays = [];
    var page = 1; // 초기 로딩 시에는 page 값을 1로 설정하여 처음부터 상품을 가져올 수 있도록 함
    var size = 10; // 한 번에 보여줄 상품 수
    var count = 0; // 초기 로딩 시 상품의 개수를 0으로 설정
    var isFirstLoad = true; // 처음 로딩 여부를 확인하는 변수

    function loadMore() {
        $.ajax({
            url: "/member/page/" + page + "/" + size,
            method: "GET",
            success: function(response) {
                var members = response;
                if (members.length > 0) {
                    if (isFirstLoad) {
                        $("tbody").empty(); // 처음 로딩 시에만 기존의 상품 내용을 모두 지우고 다시 출력
                        displayedJoinDays = []; // displayedJoinDays 배열도 초기화
                        isFirstLoad = false; // isFirstLoad 값을 false로 설정
                    }

                    for (var i = 0; i < members.length; i++) {
                        var member = members[i];
                        var row = "<tr>" +
                            "<td class='board-no'>" + member.username + "</td>" +
                            "<td>" + member.joinDay + "</td>" +
                            "<td><button type='button' class='btn btn-outline-warning' id='detail-memberOrder'>주문내역</button></td>" +
                            "<td>" + (count + i + 1) + "</td>" +
                            "</tr>";
                        $("tbody").append(row);
                        displayedJoinDays.push(new Date(member.joinDay)); // 등록일자를 날짜 형식으로 저장
                    }
                    count = members.length; // count 값을 상품의 개수로 설정
                    page++; // page 값을 증가시킴
                } else {
                    $("#loadMoreBtn").hide();
                    $("tbody").append("<tr style='height: 200px;'><td colspan='6'><a href='/member/admin-member' style='text-decoration: none; color:darkgray;'>더 이상 등록된 회원이 없습니다.</a></td></tr>");
                }
            },
            error: function() {
                alert("회원정보를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    }

    $("#loadMoreBtn").click(function() {
        loadMore();
    });

    loadMore(); // 페이지 로딩 시 초기 상품 로드
});
</script>
<style>
.btn-outline-warning{
	margin-top: 20px;
}

#admin-mainPage{
	margin: 0px;
	margin-left:1500px;
}
</style>
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
        <aside id="admin-mainPage">
			<button type="button" class="btn btn-outline-warning" onclick="location.href='/admin'">관리</button>
		</aside>
        <section>
            <table class="table table-hover" id="board-table">
                <thead>
                    <tr>
                        <th class="board-no">회원아이디</th>
                        <th>가입일</th>
                        <th>주문서</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${members}" var="member" varStatus="status">
                        <tr>
                            <td class="board-no">${member.username}</td>
                            <td>${member.joinDay}</td>
                            <td>
                                <button type="button" class="btn btn-outline-warning" id="detail-memberOrder">주문내역</button>
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
