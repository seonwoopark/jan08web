<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
	table {
		width:900px;
		height:400px;
		border-collapse: collapse;
	}
	th{
		background-color : green;  
	}
	td {
		border-bottom: 1px solid gray;
		text-align: center;
	}
	.title{
		text-align: left;
		width:50%;
	}
	tr:hover{
		background-color: #E3F6CE;
	}
	
	.w1{
		width:10%
	}
	.w2{
		width:20%
	}
	.title a {
		text-decoration: none;
	}
	
	.title a:visited{
		color:black;
	}
	
	.title a:link{
		color:black;
	}
	
	.title a:hover{
		color:orange;
		font-weight: bold;
	}
	
	.paging{
		margin:0 auto;
		width:500px;
		height: 30px;
		margin-top: 10px;
		line-height: 30px;
		text-align: center;
	}
	.comment{
		color:red;
		font-size: small;
		font-weight: bold;
	}
	.currentBtn{
		background-color: skyblue;
		
	}
</style>
</head>
<body>
	<div class="container"><%@ include file = "menu.jsp" %>
		<div class="main">
			<div class="mainStyle">
				<article><c:choose><c:when test="${fn:length(list) gt 0}">
		
							<table>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>날짜</th>
								<th>조회수</th>
							</tr><c:forEach items="${list }" var="row" varStatus="s">
										<tr>
											<td class="w1">${row.board_no }</td>
											<td class="title"><a href="./detail?no=${row.board_no }&page=${page}">${row.board_title } 
												<c:if test="${row.comment ne 0 }">
													<span class="comment">${row.comment }</span>
												</c:if>
											</a></td>
											<td class="w2">${row.board_write }</td>
											<td class="w1">${row.board_date }</td>
											<td class="w1">${row.board_count }</td>
										</tr></c:forEach>
							</table>
							<c:set var="totalPage" value="${total /10 }"/>
							<fmt:parseNumber var="totalPage" value="${totalPage }" integerOnly="true"/>
							<c:if test="${total % 10 gt 0 }">
								<c:set var="totalPage" value="${totalPage +1 }"/>
							</c:if>
							<c:set var="startPage" value="1"/>
							<c:if test="${page gt 5 }">
								<c:set var="startPage" value="${page - 5 }"/>
							</c:if>
							<c:set var="endPage" value="${startPage+9 }" />
							<c:if test="${startPage+9 gt totalPage }">
								<c:set var="endPage" value="${totalPage }" />
							</c:if>
							
							<div class="paging">
							
								<button <c:if test="${page eq 1 }">disabled="disabled"</c:if>  onclick="paging(1)">&lt;&lt;</button>
								
								<button <c:if test="${page -10 lt 1 }">disabled="disabled"</c:if> onclick="paging(${page - 10})">&lt;</button>
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
									<button <c:if test="${page eq p }">class="currentBtn" </c:if> onclick="paging(${p})">${p }</button>
								</c:forEach>
								<button <c:if test="${page +10 gt totalPage }">disabled="disabled"</c:if> onclick="paging(${page +10})">&gt;</button>
								
								<button <c:if test="${page eq totalPage }">disabled="disabled"</c:if> onclick="paging(${totalPage})">&gt;&gt;</button>
							</div>
							</c:when><c:otherwise>
							<h1>출력할 값이 없습니다.</h1></c:otherwise></c:choose>
							
					<c:if test="${sessionScope.mname ne null }">
						<button onclick="url('./write')">글쓰기</button>
					${sessionScope.mname }님 반갑습니다.
					</c:if>
				</article>
			</div>
		</div>
		
	
		<%-- <table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			
			<%
				for(BoardDTO i : lists){
					%>
					<tr>
						<td><%=i.getBoard_no() %></td>
						<td><%=i.getBoard_title() %></td>
						<td><%=i.getBoard_write() %></td>
						<td><%=i.getBoard_date() %></td>
						<td><%=i.getBoard_count() %></td>
					</tr>
					<%
				}
			%>
		</table> --%>
		<article>
		
		</article>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script type="text/javascript">
		function paging(p){
			location.href="/board?page="+p;
		}
	</script>
</body>
</html>