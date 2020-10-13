<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>分類查詢</title>
    <style>
        /* .table{
            border:1;
        } */
    </style>
</head>
<body>
	分類查詢
    <table border="1">
        <tr>
            <th>編號</th>
            <th>節目名稱</th>
            <th>場地</th>
            <th>購買</th>    
        </tr>
        <c:forEach items="${cate_list}" var="show" varStatus="idx">
        <tr>
				<td>${show.no}</td><td>${show.title}</td><td>${show.site}</td>
			</tr>
		</c:forEach>

        
    </table>
</body>
</html>