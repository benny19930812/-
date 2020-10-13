<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        input {
            width: 50px;
        }
    </style>
</head>

<body>
	<FORM ACTION= "./Complete">
    <h2>訂票確認頁面</h2>
    <div>您預定的節目為：</div>
    <br>
	<div>
        
        <%=request.getParameter("param1")%>
        <input type="hidden" name="param1" value='<%=request.getParameter("param1")%>'>
        
    </div>
    <br>
    <div>請輸入欲購買的票種數量 (一人兩種票種限購 5 張)</div>
    <div>
        <label>全票數量:</label>
        <input type="text" id="adlt" name="param2"><br>
    </div>
    <div>
        <label>半票數量:</label>
        <input type="text" id="half" name="param3"><br>
    </div>
    <div>
        <button onclick="cal()">計算金額</button>
    </div>
    <div id="total"></div>
    <br><br>
    <button type="submit" onclick="checkNull()">確認送出</button>



    <script>
		
        function cal() {
            let cost = 0;
            var adltCost = document.getElementById("adlt");
            var adltValue = adltCost.value;
            var halfCost = document.getElementById("half");
            var halfValue = halfCost.value;
            var totalEl = document.getElementById("total");
            var totalC = halfValue * 500 + adltValue * 1000;
            totalEl.innerHTML = ("總計: " + totalC)
            

        };

        function checkNull() {
            var adltCost = document.getElementById("adlt");
            var adltValue = adltCost.value;
            var aValue = parseInt (adltValue);
            var halfCost = document.getElementById("half");
            var halfValue = halfCost.value;
            var hValue = parseInt (halfValue);
            var totalEl = document.getElementById("total");
            console.log(adltCost);
            console.log(adltValue);
            
            if (adltValue == 0 && halfValue == 0) {
                adltCost.innerHTML = ('<input type="number" class="adlt" name="param2"><br>');
                halfCost.innerHTML = ('<input type="number" class="half" name="param3"><br>');
            alert('不可兩欄位皆為 0')
            
            }
            if ((aValue  + hValue) > 5) {
                
                adltCost.innerHTML = ('<input type="number" class="adlt" name="param2"><br>');
                halfCost.innerHTML = ('<input type="number" class="half" name="param3"><br>');
                console.log(adltValue);
                console.log(aValue + hValue);
            alert('一人兩種票種合計限購 5 張')
            
        }
    }


    </script>

</body>

</html>