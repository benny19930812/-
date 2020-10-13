<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%request.setCharacterEncoding("UTF-8");%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>確認訂單資訊</title>
    <style>
        td {
            width: 100px;
        }

        .orderNum {
            width: 30px;
        }
        .title{
         width:200px;
        }
        .total1{
        width: 120px;
        }
        .total2{
        width: 120px;
        }
        .totalprice{
        width: 120px;
        }
        
        .price2{
        width:200px;
        }
    </style>
</head>

<body>

<jsp:include page="/fragment/top.jsp" />
<div class="container">
<form name="order2" action="<c:url value='/_04_Orderlist/OrderlistServlet'/> " method="post">
<c:set var="name" value="${param.name}" scope="session"/> 
<c:set var="email" value="${param.email}" scope="session"/> 
<c:set var="tel" value="${param.tel}" scope="session"/> 
<c:set var="add" value="${param.add}" scope="session"/> 
<c:set var="totalprice" value="${param.total3}" scope="session"/> 
    <H1>確認訂單資訊</H1>
    <table class= "table table-bordered">
        <tr>
            <td>訂購人姓名
            </td>
            <td>${name}</td>
        </tr>
        <tr>
            <td>電子郵件
            </td>
            <td>
             ${email}
             </td>
        </tr>
        <tr>
            <td>電話
            </td>
            <td>
            
             ${tel}
            </td>
        </tr>
        <tr>
            <td>地址
            </td>
            <td>
             ${add}
            </td>
        </tr>
     </table><br><br> <br>  

      <table class= "table table-bordered">  
        
        <tr>
            <td class="title">節目名稱</td>
            <td>票種</td>
            <td>數量</td>
            <td>價格</td>
            <td class="price2">總價</td>
           
        </tr>
        
         </tr>
        <%--使用JSTL 執行for loop ${show.no}取map內value --%>
        <c:forEach items="${cartlist}" var="show" varStatus="idx">
        <tr>
       <!-- 傳送訂單資訊 -->
       <form name="order" action="" method="get">
		<tr>
            <td>${show.title}</td>
            <td>全票</td>
            <td>${show.halfnum}</td>  
           <td name="price" class="price" id="price" >1000</td>
            <td>${show.total1}</td>
           
         </tr>
         <tr>
         
     
         
            <td>${show.title}</td>
            <td>半票</td>
            <td>${show.halfnum}</td>         
            <td name="price" class="price" id="price" >500</td>
            <td>${show.total2}</td>
        </tr>
			</form>
			
			
		</c:forEach>       
        <tr><td>總計</td><td></td><td></td><td></td><td>${totalprice}</td><tr>

    </table><br>
    </form>
          <input type="button" value="修改訂單" name="" class="btn btn-outline-info" id="" onclick="history.back()">  
          <input type="submit" value="送出訂單" name="1" class="btn btn-outline-info" id="1">  
</div>
    
<!-- <script src="js\jquery-3.5.1.min.js"></script> -->
 <script src="https://code.jquery.com/jquery-3.5.1.js"
    integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</script>

    <script>

    $("#1").click(function () {
    	if (confirm("是否送出訂單? ")) {
    		window.location ="<c:url value='/_04_ShopCart/DelectCart'/>" 
    	} else {
    		return false;
    	}
    	
    })

    </script>

</body>

</html>