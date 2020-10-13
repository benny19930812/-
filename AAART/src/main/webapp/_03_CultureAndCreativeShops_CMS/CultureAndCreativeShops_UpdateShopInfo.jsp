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
<title>文創商店 後台管理系統 商家資料更新</title>
</head>
<body>
	<h2>文創商店 後台管理系統</h2>
	<h3>商家資料更新</h3>
	<form method="post" >
		<label>假如不確定該店家辨識碼，請先使用店家名稱進行搜尋</label><br /> 
		<a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_SearchShopsInfo.jsp' />">店家搜尋</a>
	</form>
	
	<form method="post" action="<c:url value = "CultureAndCreativeShops_UpdateShopInfo"/>">
		<table>
			<label>商店ID:</label>
			<input type="text" placeholder="請輸入ID" name="MainTypePk">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">不可空白</p>
		
			<label>店名:</label>
			<input type="text" placeholder="請輸入店名 " name="Name" >
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">店名不可空白</p>

			<label>圖片網址:</label>
			<input type="text" placeholder="請輸入圖片網址" name="RepresentImage">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">圖片網址不可空白</p>

			<label>介紹:</label>
			<textarea  placeholder="請輸入簡介" name="Intro" style="width:500px;height:80px;"></textarea>
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">簡介</p>

			<label>縣市、鄉鎮市區:</label>
			<input type="text" placeholder="請輸入縣市" name="CityName">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">範例: 臺北市 信義區 (中間需空2格)</p>

			<label>地址:</label>
			<input type="text" placeholder="請輸入地址" name="Address">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">範例: 市府路45號</p>
			
	<!-- ====================================================== -->
	
			<label>經度:</label>
			<input type="text" placeholder="請輸入經度" name="Longitude">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白</p>

			<label>緯度:</label>
			<input type="text" placeholder="請輸入緯度" name="Latitude">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白</p>

			<label>開放時間:</label>
			<input type="text" placeholder="請輸入時間" name="OpenTime">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">無格式要求</p>

			<label>連絡電話:</label>
			<input type="text" placeholder="請輸入連絡電話" name="Phone">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">不可空白、無格式要求</p>
			
			<label>傳真號碼:</label>
			<input type="text" placeholder="請輸入傳真號碼" name="Fax">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白、無格式要求</p>

	<!-- ====================================================== -->

			<label>電子郵件:</label>
			<input type="text" placeholder="請輸入電子郵件" name="Email">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白</p>

			<label>Facebook:</label>
			<input type="text" placeholder="請輸入facebook 網址" name="Facebook">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白</p>
			
			<label>店家網址:</label>
			<input type="text" placeholder="請輸入網址" name="Website">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">可空白</p>


			<label>點擊數:</label>
			<input type="text" placeholder="請輸入點擊數" name="Clicks">
			<span id=idsp class="colorGray"></span>
			<br />
			<p class="colorGray">不可空白</p>

	<!-- ====================================================== -->
	 		<button type="submit">資料送出</button>
		
		</table>

	</form>
</body>
</html>
