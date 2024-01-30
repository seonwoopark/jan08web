<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 관리</title>
<link rel="stylesheet" href="../css/admin.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/member.css?ver=0.2"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
					<h1>댓글 관리</h1>
					<div class="comments">
						<table class=commentsTable>
							<thead>
								<tr>
									<th>댓글 번호</th>
									<th>게시글 번호</th>
									<th>댓글</th>
									<th>날짜</th>
									<th>ip</th>
									<th>삭제</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="co" items="${list }">
									<tr>
										<td>${co.cno }</td>
										<td>${co.board_no }</td>
										<td>${co.comment }</td>
										<td>${co.cdate }</td>
										<td>${co.ip }</td>
										<td>${co.cdel }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<c:set var="totalPage" value="${totalcount / 10 }"/>
						<fmt:parseNumber var="totalPage" integerOnly="true" value="${totalPage }"/>
						
						<c:if test="${totalcount % 10 gt 0 }">
							<c:set var="totalPage" value="${totalPage +1 }"/>
						</c:if>
						
						<c:set var="startPage" value="1"/>
						<c:if test="${page gt 5 }">
							<c:set var="startPage" value="${page -5 }"/>
						</c:if>
						
						<c:set var="endPage" value="${startPage + 9 }"/>
						<c:if test="${endPage gt totalPage }">
							<c:set var="endPage" value="${totalPage }"/>
						</c:if>
												
						
						<div class="paging">
							<button>⏮️</button>
							
							<button>◀️</button>
							
							<c:forEach var="btn" begin="${startPage }" end="${endPage }">
								<button>${btn }</button>
							</c:forEach>
							
							<button>▶️</button>
							
							<button>⏭️</button>
						
						</div>
					</div>
				</article>
			</div>
		</div>
</body>
</html>