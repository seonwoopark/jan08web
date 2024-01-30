<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/admin.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/member.css?ver=0.2"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
<script type="text/javascript">
	$(function(){
		
		$("#searchBtn").click(function(){
			let search = $('#search').val();
			location.href="./adminboard?search="+search;
		});

		$('.changeDel').click(function(){
			let del = $(this).prev().val();
			let no = $(this).parents('tr').children().first().text();
			
			let tr = $(this).parents('tr');
			let i =$(this);
			let del2 = $(this).prev();
			$.ajax({
				url:'./adminboard',
				type:'post',
				dataType:'text',
				data:{'del':del,'board_no':no},
				success:function(result){

					if(del == 1){
						tr.attr('class',"row0");
						i.attr('class','xi-eye-off-o');
						del2.attr('value','0');
					} else {
						tr.attr('class',"row1");
						i.attr('class','xi-eye');
						del2.attr('value','1')
					}
				},
				error:function(error){
					alert("에러 : "+ error)
				}
			}) 
		})
	});
</script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menus.jsp" %>
		<!-- 본문내용 -->
		<div class="main">
			<!-- 이 페이지에 오는 모든 사람은 관리자인지 검사를 합니다.
			관리자 경우 보여주고, 로그인 하지 않았거나 일반 사용자는 로그인 페이지로 이동 시킵니다 -->
			<article>
				<h1>게시물 관리</h1>
					<c:choose><c:when test="${fn:length(list) gt 0 }">
					<div class="board">
						<div class="search">						
							<input type="text" id ="search"><button id="searchBtn">검색</button>
						</div>
							<table class="boardTable">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>글쓴이</th>
										<th>날짜</th>
										<th>댓글</th>
										<th>방문자</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody><c:forEach items="${list }" var="row">
									<tr class="row${row.del }">
										<td class="w1">${row.no }</td>
										<td class="title"><a href="/detail?page=${page }&no=${row.no }">${row.title }</a>
										<td class="w2">${row.write }</td>
										<td class="w1">${row.date }</td>
										<td>${row.comment }</td>
										<td class="w1">${row.count }</td>
										<td>
											<input type="hidden" id="del" value="${row.del }">
											<c:if test="${row.del eq 0 }"><i class="xi-eye-off-o changeDel"></i></c:if>
											<c:if test="${row.del ne 0 }"><i class="xi-eye changeDel"></i></c:if>
										</td>
									</tr></c:forEach></tbody>
							</table>
						</div>	
							<c:set var="totalPage" value="${totalCount / 10 }"/>
							<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
							
							<c:if test="${totalCount % 10 gt 0 }">
								<c:set var="totalPage" value="${totalPage + 1 }"/>
							</c:if>
							
							
							 <c:set var="startPage" value="1"/>
							 <c:if test="${page gt 5 }">
							 	<c:set var="startPage" value="${page - 5 }"/>
							 </c:if>
							 
							 
							 <c:set var="endPage" value="${startPage + 9 }"/>
							 <c:if test="${endPage gt totalPage }">
							 	<c:set var="endPage" value="${totalPage }"/>
							 </c:if>
							
							<div class="paging">
								<button onclick="paging(1)">⏮️</button>
								<button <c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if> onclick="paging(${page - 10 })">◀️</button>
								
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
									<button <c:if test="${page eq p }">class="currentBtn"</c:if> onclick="paging(${p })">${p }</button>
								</c:forEach>
								
								<button <c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if> onclick="paging(${page + 10 })">▶️</button>
								<button onclick="paging(${totalPage })">⏭️</button>
								
							</div>
						</c:when>
						<c:otherwise>
							<h1>출력할 값이 없습니다.</h1>
						</c:otherwise>
					</c:choose>
					
				</article>
		</div>
	</div>
</body>
</html>