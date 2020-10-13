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
    <title>Modify Center</title>

    <style>
        form{
            margin: auto;
            margin-top: 100px;
        }

    </style>
</head>
<body>
    <div class="container-sm">
    <form ACTION="./CRUDServlet">
    <c:forEach var='precise' items='${preciseAP}' varStatus="vs">
    <input type="hidden" name="method" value="modifComplete" />
        <div class="form-row">
          <div class="form-group col-md-3">
            <label for="inputEmail4">商品貨號</label>
            <input type="text" class="form-control" name="apid" placeholder="${precise.productId} " required="required">
          </div>
          <div class="form-group col-md-3">
            <label for="inputPassword4">商品名稱</label>
            <input type="text" class="form-control" name="APTITLE" placeholder="${precise.productTitle} " required="required">
          </div>
          <div class="form-group col-md-3">
            <label for="inputPassword4">商品類型</label>
            <input type="text" class="form-control" name="APTYPE" placeholder="${precise.productTitle}"  required="required">
          </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-2">
              <label for="inputEmail4">價格</label>
              <input type="number" class="form-control" name="APPRICE" placeholder="${precise.productPrice} " required="required">
            </div>
            <div class="form-group col-md-2">
              <label for="inputPassword4">數量</label>
              <input type="number" min="1" class="form-control" name="APNUM" placeholder="${precise.productNum} " required="required">
            </div>
          </div>

     
            <div class="form-group">
                <label for="inputAddress">照片路徑</label>
                <input type="text" class="form-control" name="APIMG" id="inputAddress" placeholder="${precise.productImg} " required="required">
              </div>
          
          <div class="form-group">
            <label for="inputAddress">商品描述</label>
            <input type="text" class="form-control" name="APDES" id="inputAddress" placeholder="${precise.productDes} " required="required">
          </div>
        
        <button type="submit" class="btn btn-primary">確認送出</button>
      
      </c:forEach>
      </form>
     <button type="button" class="btn btn-info" value="全部商品" onclick="location.href='<c:url value='14_CRUDPage.jsp' />'">返回上一頁</button>
      </div>
    
</body>
</html>