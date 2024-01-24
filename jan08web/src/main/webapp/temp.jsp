<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>temp</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					
					<c:set var="number" value="105"/>
					<c:out value="${number }"/>
					<br>
					<c:forEach begin="1" end="9" var="row" step="1">
						2 x ${row } = ${row * 2 }<br>
					</c:forEach>
					
					<c:if test="${number eq 105 }">
						number는 105입니다.<br>
						eq 11 == 5 같은 값? false
						ne 11 != 5 달라?    true
						lt 11 〈 5          false
						gt 11 〉 5          true
						le 11〈= 5          false
						ge 11 〉=5          true
						&& 
						||
						empty
						not empty
					</c:if>
					<hr>
					1~45까지 짝수만 출력하세요.
				</article>
				<article>
				
					<c:forEach begin="1" end="45" var="row">
						<c:if test="${row % 2 eq 0 }">
							${row }<br>
						</c:if>
					</c:forEach>
					
				</article>
				<article>
					
					<br>
					<c:forEach begin="1" end="10" var="row" varStatus="s">
						${s.begin } / ${s.end } 
						
					</c:forEach>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
</body>
</html>