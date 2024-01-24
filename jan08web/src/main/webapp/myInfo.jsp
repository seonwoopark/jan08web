<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 보기</title>

<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
	.history{
		border-collapse:collapse;
		width : 100%;
		min-height : 250px;
		height : auto;
	}
	.historyhead{
		background-color: green;
	}
	tr{		
		border-bottom : 1px solid; 
	}
	
	.w1{
		width:10%;
		text-align: center;
	}
	.w3{
		width:30%;
		text-align: center;
	}
	.w5{
		width:50%;
		text-align: left;
	}
	
	.w5:hover{
		color:orange;
		font-weight : bold;
	}
	tbody tr:hover{
		background-color: #D8D8D8;
	}
</style>

</head>
<body>
	<% if(session.getAttribute("mid") == null){
		response.sendRedirect("./login");
		}%>
	<div class="container">
		<header>
			<jsp:include page="menu.jsp"/>
			<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<div>
						<form action="./myInfo" method="post" onsubmit="return check()">
							<input type="password" name="newPw" id="newPw" placeholder="변경할 암호를 입력하세요.">
							<button type="submit">수정하기</button>
						</form>
					</div>
				</article>
				<article>
					<h2>방문 흔적 찾아가기</h2>
					<table class="history">
						<thead class="historyhead">
							<tr>
								<th>번호</th>
								<th>글번호</th>
								<th>글제목</th>
								<th>읽은 날짜</th>
							</tr>
						</thead>
						<tbody class="historybody">
							<c:forEach items="${readData }" var="i">
								<tr>
									<td class="w1">${i.vno }</td>
									<td class="w1">${i.board_no }</td>
									<td class="w5" onclick="location.href='./detail?no=${i.board_no}'">${i.board_title }</td>
									<td class="w3">${i.vdate }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script>
		function check(){
			var pw = document.querySelector("#newPw");
			
			if(pw.value.length>10){
				alert("비밀번호 10자리 아래로 입력하세요.");
				pw.value="";
				return false;
			} else if(pw.value.length <1){
				alert("비밀번호를 입력하세요.");
				return false;
			} else{
				return true;
			}
		}
	</script>
</body>
</html>