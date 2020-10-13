<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>加入會員</title>
<style>
 		fieldset {
            width:500px;
            margin:15px;
            border:1px solid brown
            border-radius:15px;
            margin:0px auto;
            margin-top: 100px;
            
        }
        legend {
            color:	#0080FF;
            margin-left:30px;
            font-weight:bold;
            
        }
        .st1 {
            width:450px;
            border-bottom:3px dashed 	#D0D0D0;
            margin:20px;
            padding-bottom:10px;
        }
        .sub {
            width:450px;
            margin:20px;
            text-align:center;
            margin:0px auto;
        }
        .t1 {
            width:100px;
            float:left;
            text-align:right;
        }
</style>
</head>
<body>
<jsp:include page="/_04_ST/04_topbar.jsp" />

 <form action="" method="post" enctype="multipart/form-data">
        
        <fieldset>
            <legend>會員登入</legend>
        <div class="st1">
            <label class="t1" for="account1">帳號:</label>
            <input type="text" id="account1" name="account" autocomplete="off" autofocus placeholder="guest" size="10" required>
        </div>
        <div class="st1">
            <label class="t1" for="pwd1">密碼:</label>
            <input type="password" id="pwd1" name="pwd" maxlength="6" required>
      

    </fieldset><br>
        <div class="sub">
           <input type="radio" name="remember" value="" class="remember">記住帳號密碼<br>
             <input type="submit" value="登入">
            <input type="reset" value="清除">
        </div>

       

    </form>

</body>
</html>