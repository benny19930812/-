<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="_14_shopAP.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<FORM ACTION="./ProductArray2" name="method" >
<input type="hidden" name="method" value="submit"/>
<h2>
 		<% 
	
   		List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
		for(int i=0; i < carList.size(); i++) {
			
 			ProductItem order;
			order = (ProductItem) carList.get(i);
			
			//String a = order.getProductNumAP();
			
			//String b = order.getProductPrice();
			//int pNum =Integer.parseInt(a);
			//int pPrice =Integer.parseInt(b);
   		%>
   		
   		<p><%-- order.getProdutTitle() --%></p>
   		<p><%-- order.getProductID() --%></p>
   		<p>數量：<%-- order.getProductNum() --%></p>
   		<p>單價：<%-- order.getProductPrice()--%></p>
   		<p> 小計：<%-- pNum*pPrice --%></p>
   		<p>---------------------------------------</p>
   		
   		<%} %>
</h2>
<button type="submit" name="input" >確認送出 </button>
</FORM>
             

<%--	<%= order.getProdutTitle() %>
<%= order.getProductNum() %>
<%= order.getProductPrice()%>
<%= pNum*pPrice %>
<jsp:getProperty name="orderItem" property="produtTitle" />
<jsp:getProperty name="orderItem" property="productNum" />
<jsp:getProperty name="orderItem" property="productPrice" />

			--%>
</body>
</html>