<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#best-table {
    border-collapse: separate; /* 셀 간격을 설정하기 위해 border-collapse를 separate로 설정합니다. */
    border-spacing: 50px 0; /* 원하는 셀 간격의 크기를 설정합니다. 여기서는 10px을 사용하였습니다. */
}

#best-table td {
	width: 250px;
	border: 1px solid gray;
	height: 270px;
	text-align: center;
}

#best-table td:last-child {
	margin-right: 0; /* 마지막 <td>의 오른쪽 여백 제거 */
}

#best-table td:hover {
  transform: scale(1.05); /* 마우스 커서가 위에 있을 때 이미지 크기를 1.1배로 확대 */
}

</style>
</head>
<body>
	<div id="aside-best">
		<table id="best-table">
			<tr>
				<c:forEach items="${products}" var="bestProduct" varStatus="status">
					<c:if test="${status.index < 4}">
						<td>
							<h3 style="color: red;">Best</h3>
							<a href="/product/product-read?pno=${bestProduct.pno}">
								<img class="get-product-img" src="${bestProduct.productPhoto[0].productSaveImg}" alt="${product.productName}">
							</a>
							<span>${bestProduct.productName}</span>
						</td>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>