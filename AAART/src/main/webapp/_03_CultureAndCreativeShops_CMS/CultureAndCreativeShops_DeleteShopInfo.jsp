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
<title>文創商店 後台管理系統 刪除商家資料</title>
</head>
<body>
	<h2>文創商店 後台管理系統</h2>
	<h3>刪除商家資料</h3>
	<form method="post" action="<c:url value = "CultureAndCreativeShops_DeleteShopInfo"/>">
		<table>

			<label>店家ID:</label>
			<input type="text" placeholder="請輸入店家ID " name="MainTypePk">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray"></p>
			<button type="submit">資料送出</button>
			
		</table>
	</form>

	<form method="post" >
		<label>假如不確定該店家辨識碼，請先使用店家名稱進行搜尋</label><br /> 
		<a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_SearchShopsInfo.jsp' />">店家搜尋</a>
	
	</form>
</body>
</html>