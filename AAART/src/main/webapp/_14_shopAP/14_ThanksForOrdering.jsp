<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="refresh" content="2;url=./ShopListController"> 

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
<meta charset="UTF-8">

<title>謝謝光臨</title>

<style>
@import url(https://fonts.googleapis.com/earlyaccess/cwtexfangsong.css);
 @import url('https://fonts.googleapis.com/css2?family=Amatic+SC&display=swap');

</style>
</head>
<body>
<jsp:include page="/fragment/top.jsp" />
<div class="container" style="background-color:  #ffffff; margin-top: 130px;">

<div class='menu'>
<div style="font-family:cwTeXFangSong, serif; text-align: center;"><img src="${pageContext.request.contextPath}/image/14_thxPage.png"   title="圖片提示文字" alt="123" ></div>


<div style="font-family:cwTeXFangSong, serif;text-align: center;"><a href="<c:url value='/_14_shopAP/14_serach.jsp' />"> 或按這裡</a></div>
</div>
</div>

</body>
</html>