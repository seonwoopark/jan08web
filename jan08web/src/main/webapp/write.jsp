<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<script type="text/javascript" src="./js/menu.js"></script>
<script>
    $(document).ready(function() {
        $('#summernote').summernote({
            height: 500
        });
    });
</script>

<style type="text/css">
	#title{
		width:100%;
		height:30px;
		margin-bottom:10px;
	}
</style>

</head>
<body>
	<div class="container1">
		<header>
			<jsp:include page="menu.jsp"/>
		</header>
	 	<div class="main">
			<div class="mainStyle">
				<article>
					<h1>글쓰기</h1>
					<div>
						<form action="./write" method="post">
							<input id="title" type="text" name="title">
							<textarea name="content" id="summernote"></textarea>
							<input type="hidden" name="mno" value="${sessionScope.mno }">
							<button type="submit">저장하기</button>
						</form>
					</div>
				</article>
			</div>
	 	</div>
	 	
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
 	</div>
</body>
</html>