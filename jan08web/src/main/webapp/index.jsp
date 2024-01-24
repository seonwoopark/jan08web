<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
	<div class="container1">
		<header>
			<jsp:include page="menu.jsp"/>
			<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<c:set var="number" value="105"/>
					<c:out value="${number }"/> <br>
					
					<c:forEach begin="1" end="9" var="row" step="2">
							<p>2 * ${row } = ${row * 2 }</p>
					</c:forEach>
					
					<c:if test="${number eq 105 }">
						number 는 105입니다.<br>
					</c:if>
					<
					<!-- 
						eq ==
						ne !=
						lt <
						gt >
						le <=
						ge >=
						
					 -->
					 <hr>
					 <c:forEach begin="1" end="45" var="i">
					 	<c:if test="${ i%2 eq 0 }">
					 		${i }<br>
					 	</c:if>
					 </c:forEach>
				</article>
				<article>
					<br>
					<c:forEach begin="1" end="10" var="row" varStatus="s">
						${s.count }
					</c:forEach>
				</article>
				
				<article>
					<h1>index입니다.</h1>
					<a href="./board">board</a>
				</article>
			</div>
		</div>
		
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>

</body>
</html>