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

h2, h3 {
	text-indent: 80px;
	text-align: left;
	font-style: normal;
}

form {
	width: 1000px;
	margin: auto;
	padding: 20px;
	border-color: transparent;
	display: block;
	background-color: #F5F1E3;
	padding: 20px;
}

.colorGray {
	color: #9D9D9D;
	font-size: 12px;
}
</style>
<meta charset="UTF-8">
<title>文創商店 後台管理系統 商家資料簡易查詢</title>
</head>
<body>
	<h2>文創商店 後台管理系統</h2>
	<h3>商家資料簡易查詢</h3>
	<form method="post"
		action="<c:url value = "CultureAndCreativeShops_SearchShopsInfo"/>">
		<table>
			<label>店名:</label>
			<input type="text" placeholder="請輸入店名 " name="Name">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">店名不可為空白</p>
		</table>

	</form>
</body>
</html>