<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSR</title>
</head>
<body>
<div>
<p>一共有<c:out value="${kidsAmount}"/>位孩童需要幫助</p>
	<FORM ACTION="<c:url value='ChooseCategory' />" method="post">
		請選擇您想贊助這些孩童們參加何種活動
		<select name="activity_category" size="1" >
          <option value="1">音樂</option>
          <option value="2">戲劇</option>
          <option value="3">舞蹈</option>
          <option value="4">親子</option>
          <option value="5">獨立音樂</option>
          <option value="6">展覽</option>
          <option value="7">講座</option>
          <option value="8">電影</option>
          <option value="11">綜藝</option>
          <option value="13">競賽</option>
          <option value="14">徵選</option>
          <option value="15">其他</option>
          <option value="16">未知分類</option>
          <option value="17">演唱會</option>
          <option value="19">研習課程</option>
        </select>
    	<BR>
    	<BR>
    	<INPUT TYPE="submit" value="查詢藝文活動"> 
 	</FORM>

</div>
</body>
</html>