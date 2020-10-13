<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<form method="post" action ="<c:url value="CategoryBarServlet" />"  >
		<hr>
		<button name="CategoryId" type="submit" value=1>音樂</button>
		<button name="CategoryId" type="submit" value=2>戲劇</button>
		<button name="CategoryId" type="submit" value=3>舞蹈</button>
		<button name="CategoryId" type="submit" value=4>親子</button>
		<button name="CategoryId" type="submit" value=5>獨立音樂</button>
		<button name="CategoryId" type="submit" value=6>展覽</button>
		<button name="CategoryId" type="submit" value=7>講座</button>
		<br>
		<button name="CategoryId" type="submit" value=8>電影</button>
		<button name="CategoryId" type="submit" value=11>綜藝</button>
		<button name="CategoryId" type="submit" value=13>競賽</button>
		<button name="CategoryId" type="submit" value=14>徵選</button>
		<button name="CategoryId" type="submit" value=15>其他</button>
		<button name="CategoryId" type="submit" value=17>演唱會</button>
		<button name="CategoryId" type="submit" value=19>研習課程</button>
		<hr>
	</form>
