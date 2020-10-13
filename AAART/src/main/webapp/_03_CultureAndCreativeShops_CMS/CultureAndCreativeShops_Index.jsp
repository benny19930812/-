<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<style>
	html, body {
  		margin: 0;
  		padding: 0;
  		background:#FFFFFF;
  		font-family: "Helvetica", "Arial","微軟正黑體", sans-serif;
	}

h2{
	text-indent: 80px;
	text-align:left;
	font-style: normal;
}

table{
	background-color: #F5F1E3;
}



/*  選單之外表格  */
.menuOuter {
    border: 1px solid #FFF;
    table-layout: fixed;
    width: 100%;
}
/*  選單之內表格  */
.menuInner {
    border: 1px solid #FFF;
    width: 100%;
    table-layout: fixed;
}

.menuData  {
    border: 1px solid #FFF;
     width: 40px;
}

.menu {
  margin: 0 1.5em;
}

.highlight {
  background-color: #FFF;
  margin: 0.5em 0;
  border: 2px solid #FFF;  
  padding: 0.5em;
  font-style: italic;
}
.mainMenu {
  overflow:hidden;
  white-space: nowrap;
  color:#DDDBCB;
}

.menu{
	text-align:center;
	float: left;
	width:42px;
	height:18px;
	/* margin: [上方邊界值] [右邊邊界值] [下方邊界值] [左邊邊界值] */
	margin: 2px;
	/* border-width  border-style border-color */
	border: 1px solid #FFF;
	/*  padding: [上方留白值] [右邊留白值] [下方留白值] [左邊留白值]   */
	padding: 3px 3px 3px 3px;
}

	</style>
	<meta charset="UTF-8">
	<title>文創商店	後台管理系統</title>
</head>

<body>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eDM.css" type="text/css" /> --%>

<h2>文創商店   後台管理系統</h2>

<c:set var='debug' value='true' scope='application' />
<table class='menuOuter' >
  <tr height="48px">
    <td width="60px" rowspan='2'>
    
<!-- 對照      -->
<!--     <img width="60px" height="40px" -->
<%--       src="${pageContext.request.contextPath}/images/BookStore.gif"> --%>

<!--     <img width="60px" height="40px" -->
<%--       src="<c:url value='/images/BookStore.gif'  />">   --%>
      
    </td>
    <td>
      <table class='menuInner' > 
        <tr>
          <td class='menuData'>
            <div class='menu'>
			  <c:if test="${empty LoginOK}">
				<a href="<c:url value='/_03_Culture_And_Creative_Shops_CMS/login.jsp' />">
				   
				</a>
              </c:if>
			</div>
		  </td>
		
		  <td class='menuData'>
			<div class='menu'>
			  <c:if test="${ funcName != 'CREATE' }">
			     <a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_CreativeNewShop.jsp' />">
				     新增 
				 </a>
			  </c:if>
			  <c:if test="${ funcName == 'CREATE' }"> 
                                              新增
              </c:if>
			</div>
		  </td>
		  
		  <td class='menuData'>
			<div class='menu'>
			  <c:if test="${ funcName != 'UPDATE' }">
				 <a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_UpdateShopInfo.jsp' />">
				    修改 
			     </a>
			  </c:if>
			  <c:if test="${ funcName == 'UPDATE' }"> 
                                                修改
              </c:if>
			</div>
		  </td>
		  
		  <td class='menuData'>
			<div class='menu'>
		      <c:if test="${ funcName != 'DELETE' }">
				<a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_DeleteShopInfo.jsp' />">
                                             刪除
                </a>
              </c:if>
			  <c:if test="${ funcName == 'DELETE' }"> 
                                             刪除
              </c:if>
			</div>
		  </td>
		  
		   <td class='menuData'>
			<div class='menu'>
		      <c:if test="${ funcName != 'SEARCH' }">
				<a href="<c:url value='/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_SearchShopsInfo.jsp' />">
                                             檢索
                </a>
              </c:if>
			  <c:if test="${ funcName == 'SEARCH' }"> 
                                             檢索
              </c:if>
			</div>
		  </td>
          
		  <td class='menuData'>
		  <div class='menu'>
			<c:if test="${ funcName != 'REG' }">
			   <a href="<c:url value='/_01_register/register.jsp' />">   </a>
			</c:if> 
			<c:if test="${ funcName == 'REG' }"> 
			 
            </c:if>
            </div>
          </td>
		  <td class='menuData'>
		  <div class='menu' style='width:50px;'>
			<c:if test="${ funcName != 'IND' }">
			   <a href="<c:url value='/index.jsp' />">   </a>
			</c:if>
			</div>
		  </td>
		  
		  <td class='menuData'>
		  <div class='menu'>
			<c:if test="${ ! empty LoginOK }">
			   <a href="<c:url value='/_02_login/logout.jsp' />">
  			 
	           </a>
			</c:if>
			</div>
		  </td>
		  <td class='menuData'>
           	<c:if test="${! empty LoginOK }">
               <img height='40px' width='30px'
	src='${pageContext.request.contextPath}/_00_init/getImage?id=${LoginOK.memberId}&type=MEMBER'>
	
			</c:if>
		  </td> 
		</tr>
		<tr height='20px'>
			<td width='300px' colspan='9' ><small>${pageContext.session.id }</small>
			</td>
		</tr>
	  </table>
	<tr>
      <td>
	  <hr style="color: #f00; background-color: #f00; height: 2px;">
	  </td>
	</tr>
</table>
</body>
</html>