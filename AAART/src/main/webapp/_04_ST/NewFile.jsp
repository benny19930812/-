<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
/* 下拉按鈕樣式 */
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

/* 容器 <div> - 需要定位下拉內容 */
.dropdown {
    position: relative;
    display: inline-block;
}

/* 下拉內容 (預設隱藏) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

/* 下拉式選單的連結 */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

/* 滑鼠移上去後修改下拉式選單連結顏色 */
.dropdown-content a:hover {background-color: #f1f1f1}

/* 在滑鼠移上去後顯示下拉式選單 */
.dropdown:hover .dropdown-content {
    display: block;
}

/* 當下拉內容顯示後修改下拉按鈕的背景顏色 */
.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
</style>
<body>


<div class="dropdown">
  <button class="dropbtn">下拉式選單</button>
  <div class="dropdown-content">
    <a href="#">線上程式語言教學練習 1</a>
    <a href="#">線上程式語言教學練習 2</a>
    <a href="#">線上程式語言教學練習 3</a>
  </div>
</div>




 <table class="table table-hover text-center">
		      <tr>
		        <th style="text-align:center;">職業</th>
		        <th style="text-align:center;">熱門職業</th>
		        <th style="text-align:center;">操作</th>
		      </tr>
		      
		      <c:forEach items="${map.xxx}"  var="list"> 
		        <tr id="tr_${list.id}" style="border-bottom: 1px solid #DDDDDD;">
		          <td style="display: none;">${list.id}</td>
		          <td>${list.occupation}</td>
		          <td>
		           <c:if test="${list.xxx==1}">不熱門</c:if>
		           <c:if test="${list.xxx==2}">熱門</c:if>
		          </td>
		          <td>
		         	 <div class="button-group">			         
			          <a class="button border-yellow" href="#" onclick="return del(${list.id})"><span class="icon-plus-square-o"></span>刪除</a>
		          	 </div>
		          </td>
		        </tr>
		      </c:forEach>
	   
         </table>
<table border="1">
	<tr><th>ID</th><th>標題</th><th>時間</th><th>操作</th></tr>
	<tr id="tr_1"><td>1</td><td>標題1</td><td>時間1</td><td><a href="javascript:void(0)" onclick="del_tr(1)">刪除</a></td></tr>
	<tr id="tr_2"><td>2</td><td>標題2</td><td>時間2</td><td><a href="javascript:void(0)" onclick="del_tr(2)">刪除</a></td></tr>
	<tr id="tr_3"><td>3</td><td>標題3</td><td>時間3</td><td><a href="javascript:void(0)" onclick="del_tr(3)">刪除</a></td></tr>
	<tr id="tr_4"><td>4</td><td>標題4</td><td>時間4</td><td><a href="javascript:void(0)" onclick="del_tr(4)">刪除</a></td></tr>
</table> 
<script>
function del(id){
	  if(confirm("您確定要刪除嗎?")){
		 var url = "/backstage/xxx/";
			$.ajax({
				url : url,
				type : "post",
				data : {
					"xx" :xx
				},
				success : function(job) {
					if (job==xxx) {
					    var tr = document.getElementById("tr_"+id);
					    tr.parentNode.removeChild(tr);
					}else {
						alert("刪除失敗，請重試!");
					}
				}
			});
	   }  
}


</script>



        
         
         
         <script>
function del_tr(id){
	var tr = document.getElementById("tr_"+id);
        tr.parentNode.removeChild(tr);
}
</script>
</body>
</html>