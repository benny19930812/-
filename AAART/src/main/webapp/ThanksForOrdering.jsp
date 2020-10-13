<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得藝的一天感謝您的蒞臨</title>
<style>
div {
	text-align:center;
}
p{
	font-size:28px;
}
</style>
</head>
<body style="background:#FFFFFF;">
<c:set var="funcName" value="END" scope="session"/>
<jsp:include page="/fragment/top.jsp" />

<div>
	<p>得藝的一天</p>
 	<p>非常感謝您的訂購</p>
 	<p>明天擱再來</p> 
</div>
</body>
</html>