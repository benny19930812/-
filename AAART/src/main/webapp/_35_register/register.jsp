<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<title>加入會員</title>
<style type="text/css">
<!--
body {
	background-attachment: fixed;
	background-color: #FFFFFF;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}
.myBorder {
	color:#FFFF99;
	border: thin dotted #FFFFFF;
}
h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:8em;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:white;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.9em; 
}
/* 主要內容的區塊 */
#content {
  width: 700px ;
  margin-left: auto ;
  margin-right: auto ;
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A9A9A9;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a9a9a9;
}

#errorMsg {
    position:relative;
    top:0px; 
    left:0px;    
	color:#FF0000;
	font-size:0.8em;
}
-->
</style>
<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId(){   
	 document.forms[0].mid.focus();   // 將游標放在mid欄位內
}
</script>
</head>
<body onLoad="setFocusToUserId()" >
<c:set var="funcName" value="REG" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/top.jsp" />
  <div id="content"> 
  <Table style="background-color: #F5F1E3; cellspacing:0;" >
     <TR height="60" >
         <TD>
         <TABLE style="cellspacing:1; " >
         <TR>
             <TD width="770" colspan='3' align="center" >
                 <Font color=black size='5'>${AppName}</Font>
             </TD>
         </TR>
         <TR>
             <TD width="270" ></TD>
             <TD width="230"  align="center">
                 <Font color=black size='4'>加入會員</Font>
             </TD>
            
         <!-- 此區塊顯示程式執行後的訊息 -->
             <TD width="270" align="left"><font size="-1" color="#FF0000">
                 ${MsgMap.InsertNG}${MsgMap.errorSaveData}</font>
             </TD>
         </TR>
         <TR>          
          <TD width="770" colspan='3'  align="center">
                 <Font color="red" size='2' face="標楷體">${MsgMap.passwordError}</Font>
             </TD>
          </TR>                    
         </TABLE>
       
     <TR><TD colspan="3">
  <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='register.do' />"  id="register.do" >
      <label class="fontSize" >帳號：</label>
      <input type="text" name="mid" value="${param.mid}" class="fieldWidth" style="width: 180px;">
      <!-- 
         注意value屬性的內容以及顯示錯誤訊息的寫法
      -->
      <font size="-1" color="#FF0000">${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >密碼：</label>
      <input type="password" name="password" value="${param.password}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPasswordEmpty}</font>      
      <br/>
      
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="password2" value="${param.password2}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPassword2Empty}</font>            
      <br/>
      
      <label class="fontSize" >姓名：</label>
      <input type="text" name="name" value="${param.name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>
      
      <label class="fontSize" >地址：</label>
      <input type="text" name="address" value="${param.address}"  class="fieldWidth" style="width: 320px;">
      <font color="red" size="-1">${MsgMap.errorAddr}</font>
      <br/>
      
      <label class="fontSize" >電子郵件：</label>
          <input type="text"  name="eMail" value="${param.eMail}"   class="fieldWidth" style="width: 300px;">
          <font color="red" size="-1">${MsgMap.errorEmail}</font>
      <br/>
      
      <label class="fontSize" >電話：</label>
      <input type="text"  name="tel" value="${param.tel}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>
      
      <label class="fontSize" >申請企業會員：</label>
      <input type="checkbox"  name="bu" value="y"    class="fieldWidth" style="width: 120px;">
      是的
      <font color="red" size="-1">${MsgMap.errorbu}</font>
      <br/>      
      
      <div id="btnArea" align="center">
         <input type="submit" class="btn btn-info" name="submit" id="submit" value="儲存"/>
         <input type="reset" class="btn btn-info" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</TD>
</TR>
</Table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>