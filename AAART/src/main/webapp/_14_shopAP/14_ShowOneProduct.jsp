<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Document</title>
    
    <style type="text/css">
    @import url(https://fonts.googleapis.com/earlyaccess/cwtexfangsong.css);
 	@import url('https://fonts.googleapis.com/css2?family=Amatic+SC&display=swap');
    
    div{
    	font-family:cwTeXFangSong, serif;
    	font-size: large
    }
    </style>
    <jsp:include page="/fragment/top.jsp" />
</head>

<body>


<div class="container" style="margin-top:100px">
            <div class="row justify-content-md-center">
            <div class="btn-toolbar mb-3" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                    <button type="button" class="btn btn-info" value="全部商品" onclick="location.href='<c:url value='/_14_shopAP/ShopListController' />'">全部商品</button>
                    <div class="btn-group" role="group">
                        <button id="btnGroupDrop1" type="button" class=" btn btn-info dropdown-toggle"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            書寫用品
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item" href="<c:url value='/_14_shopAP/ShopListController?query=pen' />">鋼筆</a>
                            <a class="dropdown-item" href="<c:url value='/_14_shopAP/ShopListController?query=ink' />">墨水</a>
                        </div>
                    </div>

                    <div class="btn-group" role="group">
                        <button id="btnGroupDrop1" type="button" class="btn btn-info dropdown-toggle"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            生活用品
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item" href="<c:url value='/_14_shopAP/ShopListController?query=home' />">家飾布置</a>
                            <a class="dropdown-item" href="<c:url value='/_14_shopAP/ShopListController?query=cook' />">精選器皿</a>
                            <a class="dropdown-item" href="<c:url value='/_14_shopAP/ShopListController?query=deco' />">飾品小物</a>
                        </div>
                    </div>
                </div>
                <c:if test="${ funcName != 'ORD' }">
       			 <button type="button" style="margin-left: 10px;" class="btn btn-outline-info" value="全部商品" onclick="location.href='<c:url value='/_14_shopAP/OrderListServlet' />'"> 訂單查詢 </button>
				</c:if>
                <FORM ACTION="./ProductArray">
                <input type="hidden" name="method" value="order"/>
                <input type="submit" style="margin-left: 10px;" class="btn btn-outline-info" name="check" value="前往結帳頁面">
                </FORM>
           </div>     
            </div>
        </div>
        <br>





    <div class="container">
        <div class="card mb-3 " style="max-width: 800px; margin: auto; margin-top: 10px;">
            <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${oneProsuct.productImg}" class="card-img" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <FORM ACTION="./ProductArray" name="method" value="selectItem">
                            <h4 class="card-title">${oneProsuct.productTitle}</h4>
                            <p class="card-text">${oneProsuct.productId}</p>
                            
                            <p class="card-text">
                                <fmt:formatNumber value="${oneProsuct.productPrice}" type="number" />元</p>

                            <span>請輸入數量：
                                <input type="number" min="1" max="${oneProsuct.productNum}" name="orderNum" value="1">
                            </span>

                            <hr>
                            <p class="card-text" style="font-size: x-large"><small class="text-muted">${oneProsuct.productDes}</small></p>
                            <input type="hidden" name="method" value="selectItem" />
                            <input type="hidden" name="productID" value="${oneProsuct.productId}" />
                            <input type="hidden" name="orderPrice" value="${oneProsuct.productPrice}" />
                            <input type="hidden" name="orderImg" value="${oneProsuct.productImg}" />
                            <%--<input type="hidden" name="orderNum" value="${num}" />--%>
                            <input type="hidden" name="orderDes" value="${oneProsuct.productDes}" />
                            <input type="hidden" name="orderDes" value="${oneProsuct.productDes}" />
                            <div><button type="submit" name="param1" value="${oneProsuct.productTitle}"
                                class="btn btn-info btn-lg btn-block btn-sm">加入購物車</button></div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>

</html>