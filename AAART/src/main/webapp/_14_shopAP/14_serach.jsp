<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Objects"%>
<%@ page import="_14_shopAP.*" %>
<%@ page import="_14_init.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>


<jsp:useBean id="ProductItem" class="_14_shopAP.ProductItem" scope="session"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FrontPage</title>
    <style>
        table{
        
            border: solid blue 2px;
             border-collapse: collapse;
        }
        td{
            border:green solid 2px;
        	text-align : center
        }
        
        .td1{
        	width: 300px;
        	
        }
        
       
    </style>
</head>

<body>
    <h1>商城首頁</h1>
    
   <%
       	DAOPage14 daoPage = new DAOPage14(); 
          	Product product = new Product();
          	
       	List<Product> listProducts = product.listProducts();
           request.setAttribute("listProducts", listProducts);
           int total = listProducts.size();
           int pages = 5;
           double temp = total/pages;
           int tpages = Double.valueOf(Math.ceil(temp)).intValue();
           
           int currentPage = 1;
           int carSize=0;
           
           String sCurrentPage = String.valueOf(pageContext.getRequest().getAttribute("currentPage"));
           if(Objects.equals(sCurrentPage, "null") || Objects.equals(sCurrentPage, "")){
           	pageContext.getRequest().setAttribute("currentPage", currentPage);
           }else{
           	currentPage = Integer.parseInt(sCurrentPage);
           }
          
           if(pageContext.getRequest().getAttribute("carSize")==null){
           	pageContext.getRequest().setAttribute("carSize", 0);
           }
       %>
   
    <h3>這是商城首頁的副標題之類的</h3>
    <div class='menu'>
			  <c:if test="${ funcName != 'ORD' }">
				 <a href="<c:url value='/OrderListServlet' />">
				     訂單查詢 
			     </a>
			  </c:if>
    <FORM ACTION="./ProductArray">
    <input type="hidden" name="method" value="order"/>
    <div><span>購物車項目筆數： </span><span name="carSize"><%=request.getAttribute("carSize") %></span></div>
    <input type="submit" name="check" value="前往結帳頁面">
 	</FORM>
 	<br>
 	<br>
 	
 	<FORM ACTION="./ProductArray">
	<input type="hidden" name="method" value="changePage"/>
    <div><span>目前位於第&nbsp<%=currentPage%>&nbsp頁，共&nbsp<%=tpages %>&nbsp頁</span></div>
    <span>前往第</span>
    <select name="currentPage" onchange="this.form.submit()" >
    
    <% for(int i=1 ; i <= tpages ; i++) { %>
	<% if(i==currentPage){ %>
		<option selected text-align : center>&nbsp<%= i%>&nbsp</option>
	<% }else{ %>
	
	<option><%= i%></option>
	
	<% }} %>

    </select>
    <span>頁</span>
    </FORM>

<br>    
<br>    
    
   <!--  <input type="submit"> -->
    
   
    <table id="mytable" >
    <thead>
    <tr>
    <th>商品一覽</th>
    <th>商品描述</th>
    </tr>
    </thead>
    <tbody>
    
    
    <%
    List<Product> subList = listProducts.subList((currentPage-1)*pages, currentPage*pages);
    for (Product productItem : subList) {
    %> 
    <FORM ACTION="./ProductArray" name="method" value="selectItem">
    <input type="hidden" name="method" value="selectItem"/>
	<input type="hidden" name="currentPage" value="<%=currentPage%>"/>
            
			
            <tr>
                <td class="td1" ><img src="<%=productItem.getProductImg()%>"  width="200" height="200" title="圖片提示文字" alt="123" ></td>
                
                <td rowspan="6" width="600px"><%=productItem.getProductDes()%>和<%=productItem.getProductId()%>和<%=productItem.getProductPrice() %></td>
            </tr>
            <tr>            
                <td class="td1" style="text-align: center ;" name="pTitle"><%=productItem.getProductTitle()%></td>            
            </tr>
            <tr>
                <td class="td1" style="text-align: center;" name="Price"><%=productItem.getProductPrice()%>$NTD</td>
                <input type="hidden" name="productID" value="<%=productItem.getProductId()%>"/>
           		<input type="hidden" name="orderPrice" value="<%=productItem.getProductPrice()%>"/>
           		<input type="hidden" name="orderImg" value="<%=productItem.getProductImg()%>"/>
            </tr>
            <tr>             
                <td class="td1" style="text-align: center;">目前庫存： <%=productItem.getProductNum()%></td>
            </tr>
            
            <tr>             
                <td class="td1" style="text-align: center;">請選擇數量：
                 <select name="orderNum"  >
    
   			 	<% for(int i=1 ; i <= (productItem.getProductNum()) ; i++) { %>

				<option><%= i%></option>
	
				<% } %>
    			</select>
				</td>
                
            </tr>
            
            <tr>
            <td style="text-align: center;"><button type="submit" name="param1" 
            value="<%=productItem.getProductTitle() %>" >加入購物車 </button></td>
            
            </tr>
            </FORM>
            
        <% } %>
       
           
            
        </tbody>
    </table>
    
    
    
    
    
</body>
</html>