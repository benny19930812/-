<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<meta charset="UTF-8">
<title>顯示表演場次</title>
<style type="text/css">
h1 {
	text-align: center;
	padding: 20px 25px;
}
table{
	border:1px solid navy;
	margin: 0 auto;
}
th, td {
	text-align:center;
	padding: 3px;
}
.title {
	background-color:#B0C4DE;
}
</style>
</head>
<body style="background:#FFFFFF;">
<!-- 下列敘述設定變數funcName的值為SHO，top.jsp 會用到此變數 -->
<c:set var="funcName" value="CSR" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/top.jsp" />
<H1>您選擇的活動類別:${categoryName}</H1>
<table>
	<tr class="title"><th>名稱</th><th>表演場地</th><th>地址</th><th>是否收費</th><th>價錢</th><th>時間</th><th>售票單位</th><th>預約該場活動</th></tr>
	<c:forEach var='BeanToken' items='${categoryList}' varStatus='vs'>
		<c:choose>
			<c:when test="${vs.count % 2 == 0}">
				<c:set var='color' value='#ACD6FF' />
			</c:when>
			<c:otherwise>
				<c:set var='color' value='#7AB8CC' />
			</c:otherwise>
		</c:choose>
		<tr bgcolor='${color}'>
			<td>${BeanToken.title}</td>
			<td>${BeanToken.location}</td>
			<td>${BeanToken.locationName}</td>
			<td>${BeanToken.onSales}</td>
			<td>${BeanToken.price}</td>
			<td>${BeanToken.time}</td>
			<td>${BeanToken.mainUnit}</td>
			<td><input type=button value="贊助孩童參與此活動" onclick="window.location='orderAct.jsp'"></td>
		</tr>
	</c:forEach> 
</table>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
