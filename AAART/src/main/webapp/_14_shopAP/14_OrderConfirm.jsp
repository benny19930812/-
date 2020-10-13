<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ page import="_14_shopAP.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
%>
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CheckOrderPage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">

function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='ProductUpdateServlet?cmd=DEL&productIdAP=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}

function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		console.log("key= "+key);
		document.forms[0].action="<c:url value='ProductUpdateServlet?cmd=MOD&productIdAP=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}


function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}

function Abort() {
	if (confirm("確定清空購物車? ") ) {
		return true;
	} else {
		return false;
	}
}


</script>

<style>
    table{
        margin: auto;
        margin-top: 50px;
    }

    table, th, td {
        
  border: 1px solid black;
  border-collapse: collapse;
  padding: 10px;
}
</style>
</head>
<body>
<table>
    <tr>
        <th>貨號</th>
        <th>圖示</th>
        <th>名稱</th>
        <th>數量</th>
        <th>價格</th>
        <th>小計</th>
        <th>編輯</th>
    </tr>
    <c:forEach varStatus="vs" var="anEntry" items="${carList.cartAP}"> 
    
    <tr>
        <td>${anEntry.value.productIdAP}<br>${anEntry.key}</td>
        <td><img src="${anEntry.value.productImgAP}" width="100" height="100" title="圖片提示文字" alt="123" ></td>
        <td>${anEntry.value.productTitleAP}</td>
        <td><input id="newQty${vs.index}" name="newQty" type="number" value="${anEntry.value.productNumAP}" min="1"  name="qty" style="width: 30px; text-align: center;"><br>
        <input type="button" value="修改" style="margin-top: 3px;" onclick="modify(${anEntry.key}, ${anEntry.value.productNumAP}, ${vs.index})"></td>
		<td><fmt:formatNumber value="${anEntry.value.productPriceAP}" type="number" /> 元</td>
		<td><fmt:formatNumber value="${anEntry.value.productPriceAP * anEntry.value.productNumAP}" type="number" /> 元</td>
        <td><input type="button" value="刪除此項商品" onclick="confirmDelete(${anEntry.key})"></td>

    </tr>
    </c:forEach>
    <tr>
        <td colspan="4"></td>
        <td colspan="3">總計：<fmt:formatNumber value="${carList.subtotal}" type="number" />元</td>
    </tr>
    <tr style="text-align: center;">
        <td colspan="2"><a href="<c:url value='/_14_shopAP/ShopListController' />">繼續購物</a></td>
        <td colspan="2"><a href="<c:url value='AbortCartServlet' />" onClick="return Abort();"> 清空購物車</a></td>
        <td colspan="3"><a href="<c:url value='CheckOutServlet' />" onClick="return Checkout(${carList.subtotal});">送出訂單</a></td>
        
    </tr>

</table>

<div class="container">
<c:if test='${not empty OrderErrorMessage}'>
		<font color='red'>${OrderErrorMessage}</font>
		<c:remove var="OrderErrorMessage"/>	
</c:if>
</div>
             
<form>
   <input type="hidden" name="a"/>
</form>
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