<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	background: #FFFFFF;
	font-family: "Helvetica", "Arial", "微軟正黑體", sans-serif;
	width: 1000px;
	margin: auto;
	padding: 20px;
	border-color: transparent;
	display: block;
	font-size:16px
}

h2 {
	text-indent: 80px;
	text-align: left;
	font-style: normal;
}

.div1 {
	margin: 0 0 50px 0;
}
</style>
<title>${SingleShowInfo.ACT_TITLE}</title>
</head>
<body>
	<header>
		<li>${SingleShowInfo.ACT_TITLE}</li>
	</header>


	<c:if test="${SingleShowInfo.ACT_IMAGE != null} ">
		<hr>
		<div style="text-align: center;">
			<img src="${SingleShowInfo.ACT_IMAGE}" style="max-width: 1000px">
		</div>
	</c:if>

	<div class="div1">
		<label>主辦單位</label>
		<p>${SingleShowInfo.ACT_MAINUNIT}</p>
	</div>

	<div class="div1">
		<label>活動地點 </label>
		<p>${SingleShowInfo.ACT_LOCATION_NAME}</p>
	</div>

	<div class="div1">
		<label>售票說明 </label>
		<p>${SingleShowInfo.ACT_PRICE}</p>
	</div>


	<div class="div1">
		<label>演出單位 </label>
		<p>${SingleShowInfo.ACT_SHOWUNIT}</p>
	</div>


	<div class="div1">
		<label>活動詳情</label>
		<p>${SingleShowInfo.ACT_DESCRIPTION}</p>
	</div>


	<div class="div1">
		<label>開始日期</label>
		<p>${SingleShowInfo.ACT_STARTDATE}</p>
	</div>

	<div class="div1">
		<label>結束日期</label>
		<p>${SingleShowInfo.ACT_ENDDATE}</p>
	</div>
</body>
</html>