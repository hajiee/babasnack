<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/board.css">
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
	if (msg !== '') {
			alert("문의글 작성은 회원만 작성가능합니다");
			window.location.href = "/member/login"; // 로그인 페이지로 리다이렉트
	}
</script>
<script>
	$(document).ready(function() {
		// 글쓴이와 날짜 정보 가져오기
		$("tbody tr").each(function() {
			var boardWriter = $(this).find("td:eq(2)").text();
			var boardDate = $(this).find("td:eq(3)").text();

			// 가져온 정보로 출력하기
			$(this).find("td:eq(2)").text(boardWriter);
			$(this).find("td:eq(3)").text(boardDate);
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
		<main id="board-main">
			<aside id="aside-board-list">
				<a href="/board/board-write">
					<button type="button" class="btn btn-outline-warning">글올리기</button>
				</a>
			</aside>
			<section>
				<table class="table table-hover" id="board-table">
					<thead>
						<tr>
							<th class="board-nav">번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="board">
							<tr>
								<td class="board-nav">${board.bno}</td>
								<td>
									<a href="/board-read?bno=${board.bno}" onclick="showCodePopup(${board.bno})">${board.title}</a> <!-- 암호 확인 팝업 -->
									<div id="CodePopup_${board.bno}" class="Code-popup"
										style="display: none;">
										<form onsubmit="event.preventDefault(); checkCode(${board.bno})">
											<input type="hidden" name="bno" value="${board.bno}">
											<input type="password" id="Code_${board.bno}" placeholder="암호를 입력하세요">
											<button type="submit">확인</button>
										</form>
										<!-- 암호 불일치 안내 메시지 -->
										<div id="CodeMismatch_${board.bno}" class="error-message" style="display: none;">암호가 일치하지 않습니다.</div>
									</div>
								</td>
								<td>${board.boardWriter}</td>
								<td>${board.boardDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pagination">
					<c:if test="${page.prev>0}">
						<li class="page-item"><a class="page-link"
							href="/list?pageno=${page.prev}">이전</a></li>
					</c:if>
					<c:forEach begin="${page.start}" end="${page.end}" var="i">
						<c:if test="${page.pageno==i }">
							<li class="page-item active"><a class="page-link"
								href="/list?pageno=${i}">${i}</a></li>
						</c:if>
						<c:if test="${page.pageno!=i }">
							<li class="page-item"><a class="page-link"
								href="/list?pageno=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${page.next>0}">
						<li class="page-item"><a class="page-link"
							href="/list?pageno=${page.next}">다음</a></li>
					</c:if>
				</ul>
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
<script>
function showCodePopup(bno) {
    // 선택된 게시글의 비밀번호 확인 팝업 표시
    document.getElementById("CodePopup_" + bno).style.display = "block";
}

function checkCode(bno) {
    const codeInput = $("#Code_" + bno).val();

    // AJAX 요청 보내기
    $.ajax({
        url: "/checkBoardCode",  // 비밀번호 유효성 검사를 처리하는 URL
        type: "POST",
        data: { bno: bno, code: codeInput },
        success: function(response) {
            if (response === true) {
                // 비밀번호가 일치하는 경우
                document.getElementById("CodeMismatch_" + bno).style.display = "none";
                // 폼 제출 진행
                $("form").submit();
            } else {
                // 비밀번호가 일치하지 않는 경우
                document.getElementById("CodeMismatch_" + bno).style.display = "block";
            }
        },
        error: function() {
            console.log("비밀번호 유효성 검사에 실패했습니다.");
        }
    });

    return false;  // 폼 제출 방지
}
</script>
</html>
