<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="_14_shopAP.*"%>
<%@ page import="java.nio.charset.StandardCharsets"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Center</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body >
<h1 style="margin-top: 50px; text-align: center;">洋行後台</h1>
<hr>
	<div class="container" >
	<h4>修改商品</h4>
	<FORM ACTION="./CRUDServlet" >
	<input type="text" name="apid" >
    <input type="hidden" name="method" value="modify"/>
	<input type="submit" name="check" value="送出">
	</FORM>
	<br><hr>
	<h4>刪除商品</h4>
	<FORM ACTION="./CRUDServlet">
	<input type="text" name="apid">
    <input type="hidden" name="method" value="delete"/>
    <input type="submit" name="check" value="刪除某項">
 	</FORM>
 	<br><hr>
 	<h4><div>新增商品<span style="color: crimson;font-size:smaller">(*字號欄位不可空白)</span></div><h4>
 	<FORM ACTION="./CRUDServlet">
 	<input type="hidden" name="method" value="insert"/>
 	<div><p>貨號<span style="color: crimson;font-size:larger">*</span>：<input type="text" name="apid" required="required" ></p>
 	<p>品名<span style="color: crimson;font-size:larger">*</span>：<input type="text" name="APTITLE" required="required" ></p></div>
 	<div>
 	<p>類型：<input type="text" name="APTYPE" required="required" ></p>
 	<p>價格<span style="color: crimson;font-size:larger">*</span>：<input type="text" name="APPRICE" required="required" ></p>
 	<p>數量<span style="color: crimson;font-size:larger">*</span>：<input type="number" name="APNUM" required="required" ></p>
 	</div>
 	<p>圖片<span style="color: crimson;font-size:smaller">(請貼上圖片連結網址)</span>：<input type="url" name="APIMG" style="width:500px;"></p>
 	<div>描述：</div>
 	<textarea style="width:300px;height:100px;" name="APDES"></textarea>
    <div><input type="submit" name="check" value="新增項目"></div>
    </FORM>
    <br><hr>
    <h4>搜尋商品貨號</h4>
 	<FORM ACTION="./CRUDServlet">
    <input type="hidden" name="method" value="search"/>
    <input type="text" name="apid">
    <input type="submit" name="check" value="搜尋商品編號">
 	</FORM>
 	<br>
 	<c:if test='${not empty searchList}'>
 	
 	<table>
        <tr>
            <th>貨號</th>
            <th>商品名稱</th>
            <th>售價</th>
            <th>庫存</th>
        </tr>
        
 	<c:forEach var="searchAP" varStatus="stat" items="${searchList}">
 	
        <tr>
            <td>${searchAP.productId}</td>
            <td>${searchAP.productTitle}</td>
            <td>${searchAP.productPrice}</td>
            <td>${searchAP.productNum}</td>
        </tr>
 	
 	</c:forEach>
    </table>
    </c:if>
    </div>

	<div style='text-align:center;'>
	
	<c:if test='${not empty OrderErrorMessage}'>
		<h3><font color='red'>${OrderErrorMessage}</font><h3>
		<c:remove var="OrderErrorMessage"/>	
		<c:remove var="SuccessMessage"/>	
	</c:if>
	
	</div>
	<div style='text-align:center;'>
	    <c:if test='${not empty SuccessMessage}'>
		<h3><font color='#1b9aaa'>${SuccessMessage}</font><h3>
		<c:remove var="SuccessMessage"/>	
		<c:remove var="OrderErrorMessage"/>	
	</c:if>
	</div>
</body>
</html>