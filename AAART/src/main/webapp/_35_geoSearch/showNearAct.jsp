<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<meta charset="UTF-8">
<title>最近活動搜尋結果</title>
<style>
	.title {
		text-align:center;
	}
	h1 {
		padding: 20px 25px;
	}
	</style>
</head>
<body>
<c:set var="funcName" value="GEO" scope="session"/>
<jsp:include page="/fragment/top.jsp" />
<div class=title>
	<h1>鄰近${userLocation}的藝文活動</h1>
</div>
<div>
	<c:set var="counter" value="0"/>
	<c:forEach var="nearAct" items="${nearActList}">
		<c:if test="${nearAct.distance != '0'}">
			<c:set var="counter" value="${counter+1}"/>
			<table class="table table-bordered table-hover table-blue">
				<tr class="">
					<th class="table-light">編號</th>
					<th class="table-warning">距離您目前位置</th>
					<th class="table-secondary">活動名稱</th>
					<th class="table-secondary">活動時間</th>
					<th class="table-success">縣市</th>
					<th class="table-danger">鄉鎮市區</th>
					<th class="table-primary">里</th>
					<th class="table-info">地址</th>
				</tr>
				<tr>
					<td class="">${nearAct.no}</td>
					<td class="">${nearAct.distance}公里</td>
					<td class="">${nearAct.title}</td>
					<td class="">${nearAct.time}</td>
					<td class="">${nearAct.city}</td>
					<td class="">${nearAct.district}</td>
					<td class="">${nearAct.village}</td>
					<td class="">${nearAct.address}</td>
				</tr>
			</table>
			<c:set var="addr" value="${nearAct.city.concat(nearAct.district).concat(nearAct.address)}"/>
			<c:if test="${counter < 31}">
				<iframe width="100%" height="250" frameborder="0" src="https://www.google.com/maps?q=${addr}&output=embed"></iframe>
			</c:if>
			<br>
			<br>
		</c:if>
	</c:forEach>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>