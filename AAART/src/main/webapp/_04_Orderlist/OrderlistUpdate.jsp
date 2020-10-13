<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%request.setCharacterEncoding("UTF-8");%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-tw">
<html>
<head>
<meta charset="UTF-8">
<title>訂單資訊修改</title>
</head>
<body>
<form method=GET action="<c:url value='/_04_Orderlist/OrderlistUpdate'/>"> 
	<jsp:include page="/fragment/top.jsp" />
<div class="container">
<p></p>
<br><h1>修改訂單</h1>
		
<table class="table table-bordered">
<tr>
            <td>訂單編號
            </td>
            <td>${orderid}</td>
        </tr>
 <tr>
            <td>訂購人姓名
            </td>
            <td>
             <input type="text" name="name" value="">
        </tr>
        <tr>
            <td>電子郵件
            </td>
            <td>
             <input type="text" name="email" value="">
             </td>
        </tr>
        <tr>
            <td>電話
            </td>
            <td>
             <input type="text" name="tel">
            </td>
        </tr>
        <tr>
            <td>地址
            </td>
            <td>
             <input type="text" name="add">
            </td>
        </tr>
</table>

 <input type="submit" value="送出修改" name="1" class="btn btn-outline-info" id="1">  
</form>
</div>
 <script src="https://code.jquery.com/jquery-3.5.1.js"
    integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</script>



 <script>

//     $("#1").click(function () {
//     	if (confirm("確認修改訂單? ")) {
//     		window.location ="<c:url value='/_04_Orderlist/OrderlistUpdate'/>" 
//     	} else {
//     		return false;
//     	}
    	
//     })

    </script>



</body>

</html>