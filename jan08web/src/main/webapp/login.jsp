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
<style>
	.login{
		margin:0 auto;
		width: 300px;
		min-height: 300px;
		background-color: #c1c1c1;
		padding :10px;
		box-sizing: border-box;
		text-align: center;
	}
	form input{
		width:250px;
		text-align: center;
	}
	
	form img{
		width:270px;
		height:220px;
	}
</style>
<script type="text/javascript">
	function err(){
		let errorBox = document.querySelector("#errorMSG");
		errorBox.style.color = "red";
		errorBox.style.fontSize="10pt";
		errorBox.innerHTML = "<marquee>올바른 암호와 아이디를 입력하세요.</marquee>";
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="menu.jsp"/>
			<!-- jsp:는 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>login</h1>
					<c:if test="${param.error ne null }">
						<script type="text/javascript">
							alert("올바른 암호와 아이디를 입력하세요.");
							
						</script>
					</c:if>
					<div class="login">
						<form action="./login" method="post">
							<img src="https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202306/25/488f9638-800c-4bac-ad65-82877fbff79b.jpg" alt="login">
							<input type="text" name="id" placeholder="ID를 입력하세요">
							<input type="password" name="pw" placeholder="PW를 입력하세요">
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>
							<div id="errorMSG"></div>
						</form>
						<button onclick="location.href='./join'">회원가입</button>
					</div>
				</article>
			</div>
		</div>
		
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err();
		</script>
	</c:if>
</body>
</html>