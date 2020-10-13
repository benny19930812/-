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
<title>文創商店 後台管理系統 商家資料查詢結果</title>
</head>
<body>
	<h2>文創商店 後台管理系統</h2>
	<h3>商家資料 簡易查詢結果</h3>
<body>
	<div align='center'>
		<table border='1' bgcolor=#F5F1E3>
			<tr>
				<td>店名</td>
				<td>簡介</td>
				<td>縣市、鄉鎮市區</td>
				<td>地址</td>
				<td>開放時間</td>

				<td>連絡電話</td>
				<td>電子郵件</td>
				<td>辨識碼</td>
				<td>店家ID</td>
				<td>點擊次數</td>
			</tr>
			<c:forEach items='${ShopsList}' var='ShopsList' varStatus='vs'>
				<tr>
					<td>${ShopsList.name}</td>
					<td>${ShopsList.intro}</td>
					<td>${ShopsList.cityName}</td>
					<td>${ShopsList.address}</td>
					<td>${ShopsList.openTime}</td>

					<td>${ShopsList.phone}</td>
					<td>${ShopsList.email}</td>
					<td>${ShopsList.website}</td>
					<td>${ShopsList.mainTypePk}</td>
					<td>${ShopsList.clicks}</td>
				</tr>
			</c:forEach>
			
			<c:if test="${SerachErrorMsg != null}">
				<tr>
					<td>${SerachErrorMsg}</td>
				</tr>
			</c:if>
		</table>
		<hr>
</body>
</html>