<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background: #FFFFFF;
}

form {
	width: 1000px;
	margin: auto;
	padding: 20px;
	border-color:transparent;
	display: block;
}
</style>
<meta charset="UTF-8">
<title>CategorySearch</title>
</head>
<body>
 <article class="article">
	<jsp:include page="03_CategoryBar.jsp" />
	<hr>
	<form method="post" action = "<c:url value = "SingleShowInfoServlet"/>" >
		<div align='center'>
			<table border='1' bgcolor=#F5F1E3>
				<tr>
					<td>節目</td>
					<td>節目資訊</td>
					<td>開始日期</td>
					<td>結束日期</td>
					<td>節目詳細資訊</td>

				</tr>
				<c:forEach items='${CategoryTable}' var='CategoryTable' varStatus='vs'>
					<tr>
						<td>${CategoryTable.ACT_TITLE}</td>
						<td>${CategoryTable.ACT_DESCRIPTION}</td>
						<td>${CategoryTable.ACT_STARTDATE}</td>
						<td>${CategoryTable.ACT_ENDDATE}</td>
						<td><button name="ShowNO" type="submit"
								value="${CategoryTable.ACT_NO}">活動詳細資訊</button></td>
						<!-- 表格超連結傳給 SingleShowInfoServlet -->
					</tr>
				</c:forEach>
			</table>
			<hr>
	</form>
	</article>
</body>
</html>