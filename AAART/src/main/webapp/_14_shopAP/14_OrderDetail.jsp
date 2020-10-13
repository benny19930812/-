<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<title>個人訂單明細</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}

#borderA {
	border: 1px solid black;
}
</style>
</head>
<body style="background: #ffffff;">
	
	<p />
	<TABLE style="margin-left: auto; margin-right: auto; background: #ffffff; border: 1px black solid;">
		<tr id='borderA' height='50'>
			<th id='borderA' align="center" colspan="7"><h3>訂單明細</h3></th>
		</tr>
		<tr id='borderA' height='36'>
			<td colspan="7">
				<table>
					<tr id='borderA'>
						<td align="Left" width="350px">
							<b>出貨地址：</b>${OrderBean.addAP}
						</td>
						<td align="center" width="300px">
							<b>訂購日期：</b>${OrderBean.date}
						</td>
						<td align="center" width="280px">
							<b>訂單編號：</b>${OrderBean.orderNoAP}
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id='borderA' height='36'>
			<th id='borderA' width="100px" align="center">品項</th>
			<th id='borderA' width="70px" align="center">單價</th>
			<th id='borderA' width="50px" align="center">數量</th>
			<th id='borderA' width="100px" align="center">總價</th>
		</tr>
		<c:set var="subtotal" value="0" />
		<c:forEach var="aBean" varStatus="stat" items="${OrderBean.items}">
			
			<tr id='borderA' bgColor="FFFFFF" height='30'>
				<td id='borderA' align="center">${aBean.orderNameAP}</td>
				
				<td id='borderA' align="right">${aBean.productPrice}&nbsp;</td>
				<td id='borderA' align="right">${aBean.productNum}&nbsp;</td>
				<td id='borderA' align="right">${aBean.productPrice*aBean.productNum}&nbsp;</td>
				
				
				<c:set var="subtotal"
					value="${ subtotal + aBean.productPrice * aBean.productNum }" />
			</tr>
		</c:forEach>
		<tr height='30'>
			<TD id='borderA' align="center" rowspan="3" colspan="5">&nbsp;</TD>
			<TD id='borderA' width="60px" align="center"><b>合 計</b></TD>
			<TD id='borderA' width="100px" align="right">
			   <fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</TD>
		
	</TABLE>
	<p />

	<div style="text-align: center">
		<a href="<c:url value='/_14_shopAP/OrderListServlet' />">回上一頁</a>&nbsp;&nbsp;
		<a href="<c:url value='/_14_shopAP/ShopListController' />">回首頁</a>
	</div>
</body>
</html>
