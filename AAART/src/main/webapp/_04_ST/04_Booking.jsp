<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>節目內容</title>
<style>
td {
	width: 100px;
}

.halfnum {
	width: 30px;
}

.adultnum {
	width: 30px;
}

.title {
	width: 200px;
}

.total1 {
	width: 120px;
}

.total2 {
	width: 120px;
}

.totalprice {
	width: 120px;
}

.price2 {
	width: 200px;
}
</style>
</head>

<body>
	<jsp:include page="/fragment/top.jsp" />
<div class="container">
	<form name="order"
		action="<c:url value='/_04_ShopCart/ShopCartServlet'/>" method="post">

		<!-- 用param.取，相當於request.getParameter-->
		<c:set var="title" value="${param.title}" scope="session" />
		<c:set var="actid" value="${param.actid}" />
		<c:set var="description" value="${param.description}" />
		<%-- 	<c:set var="actid" value="${param.actid}" scope="session"/> --%>
		<H1>節目詳細</H1>
		節目名稱:
		<P>${title}</P>
		節目簡介:
		<P>${description}</P><br><br>
		
		<H2>訂購票券</H2>
		<table class="table table-bordered">
			<tr>
				<td class="title">節目名稱</td>
				<td>票種</td>
				<td>數量</td>
				<td>價格</td>
				<td class="price2">總價</td>

			</tr>
			<tr>
				<td class="title">${title}</td>
				<td>全票</td>
				<td><input type="button" value="-" name="minus" class=""
					id="minus"> 
					<input type="text" name="adultnum"
					id="adultnum" class="adultnum" value=""> 
					<input type="button" value="+" name="plus" class="" id="plus">
				</td>

				<td name="price" class="price" id="price">NT$1000</td>
				<td>NT$ <input type="text" name="total1" id="total1"
					class="total1" value="0" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>${title}</td>
				<td>半票</td>
				<td><input type="button" value="-" name="minus2" class=""
					id="minus2"> <!-- <P name="orderNum" id="orderNum" class="orderNum"></P> -->
					<input type="text" name="halfnum" id="halfnum" class="halfnum"
					value=""> 
					<input type="button" value="+" name="plus2"
					class="" id="plus2"></td>
				<td name="price" class="price" id="price">NT$500</td>
				<td>NT$ <input type="text" name="total2" id="total2"
					class="total2" value="0" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>總計</td>
				<td></td>
				<td></td>
				<td></td>
				<td>NT$ <input type="text" name="total3" id="total3"
					class="totalprice" value="0" readonly="readonly" /></td>
			<tr>
		</table>
		<br> <input type="submit" value="確認數量" name="submit"
			class="btn btn-outline-info" id="submit">
	</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>

	<script>
		//全票
		$(document).ready(
				function() {
					/* 價格顯示 */

					/* $("select").change(function(){
						  $("#price").text("500");
						}); */

					var count = 0;
					$("#adultnum").val(count);
					$("#plus").click(
							function() {
								//設定數量上限為5
								if (count < 5) {
									count++;
								} else if (count = 5) {
									count = 5;
									alert("最多訂購五張");
								}
								$("#adultnum").val(count);
								$("#total1").val(count * parseInt(1000))
								$("#total3").val(
										count * parseInt(1000) + count2
												* parseInt(500))
							})
					$("#minus").click(
							function() {
								//設定數量下限0
								if (count > 0) {
									count--;
								} else if (count = 0) {
									count = 0;
								}
								$("#adultnum").val(count);
								$("#total1").val(count * parseInt(1000))
								$("#total3").val(
										count * parseInt(1000) + count2
												* parseInt(500))
								return count;
							})
					var count2 = 0;
					$("#halfnum").val(count2);
					$("#plus2").click(
							function() {
								if (count2 < 5) {
									count2++;
								} else if (count2 = 5) {
									count2 = 5;
									alert("最多訂購五張");
								}
								$("#halfnum").val(count2);
								$("#total2").val(count2 * parseInt(500))
								$("#total3").val(
										count * parseInt(1000) + count2
												* parseInt(500))
							})
					$("#minus2").click(
							function() {
								if (count2 > 0) {
									count2--;
								} else if (count2 = 0) {
									count2 = 0;
								}
								$("#halfnum").val(count2);
								$("#total2").val(count2 * parseInt(500))
								$("#total3").val(
										count * parseInt(1000) + count2
												* parseInt(500))
							})

				})
		//半票
	</script>

</body>

</html>