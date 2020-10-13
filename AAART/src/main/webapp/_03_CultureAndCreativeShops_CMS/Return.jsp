<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<style>
html, body {
	margin: 0;
	padding: 0;
	background: #FFFFFF;
	font-family: "Helvetica", "Arial", "微軟正黑體", sans-serif;
}

h2 {
	text-indent: 80px;
	text-align: left;
	font-style: normal;
}

table {
	background-color: #F5F1E3;
}
</style>
<meta charset="UTF-8">
<title>文創商店 後台管理系統</title>
</head>
<body>
	<h2>文創商店 後台管理系統</h2>
	<h3>回應</h3>
	<c:if test="${shopCreateMsg != null}">
		<tr>
			<td>${shopCreateMsg}</td>
		</tr>
	</c:if>
	
	<c:if test="${shopUpdateMsg != null}">
		<tr>
			<td>${shopUpdateMsg}</td>
		</tr>
	</c:if>
	
	<c:if test="${shopDeleteMsg != null}">
		<tr>
			<td>${shopDeleteMsg}</td>
		</tr>
	</c:if>
	
	<a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_Index.jsp' />">返回管理系統<a>
	
</body>
</html>