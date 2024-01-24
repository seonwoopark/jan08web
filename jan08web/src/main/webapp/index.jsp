<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
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
					<h1>index</h1>
					
					<img alt="team" src="./img/team.png">
					
					//서버에서 보내준 map : ${map }<br>
					
					<c:forEach items="${map }" var="i">
						이름 : ${i.name }<br>
						나이 : ${i.age }<br>
						주소 : ${i.addr }<br>
					</c:forEach>
					
					<c:set value="${map }" var="m" scope="page"></c:set>
					<c:out value="${m }"></c:out>
					<c:out value="${m[1]['addr'] }"></c:out>
					
					session -> jwt
					
					<c:if test="">
					
					</c:if>
					
					if(){
						참일때 실행
					}
					
					
					스프링 --> 스프링 부트...... 스프링부트 타임리프
					Controller-servlet
					Service----로직
					Model -----DAO DTO
					XML --- 확장 html
					
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
</body>
</html>