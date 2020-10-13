<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
   <HEAD>
      <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8"/>
      <TITLE>CSR</TITLE>
      <style>
      h1, form {
      	text-align:center
      }
      </style>
   </HEAD>
<BODY BGCOLOR="#FFFFFF">
<c:set var="funcName" value="CSR" scope="session"/>
<jsp:include page="/fragment/top.jsp" />
	<H1>
	贊助偏鄉孩童參加藝文活動<br> 
	或是<br> 
	訂購員工福利票</H1>

 	<form action="<c:url value='FoundingKids'/> " method="get">
		<div class="searchBox">
			<input type="text" class="" name="kidsLocation" value="">
		</div>
    	<br>
		<div class="submitButton">
			<input type="submit" class="" name="submit" value="地點查詢偏鄉孩童待媒合人數"> 
		</div>
	</form>
</BODY>
</HTML>