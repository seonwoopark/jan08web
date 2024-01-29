<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/admin.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/member.css"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
<title>Insert title here</title>

<script>
	$(function(){
		$('select[name=grade]').on('change',function(){
			/* let val = $(this).val();
			alert(val); */			
			let mno = $(this).closest("tr").children().first().text();
			
			let grade = $(this).val();
			
			let form = $('<form></form>');
			form.attr('method','post');
			form.attr('action','./members');
			form.append($('<input/>',{type:'hidden',name:'mno',value:mno}));
			form.append($('<input/>',{type:'hidden',name:'grade',value:grade}));
			<c:if test="${param.grade ne null}">
			form.append($('<input/>', {type : 'hidden', name : 'currentgrade', value : ${param.grade}}));
			</c:if>
			form.appendTo('body');
			form.submit();
		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<%@ include file="menus.jsp" %>
		<div class="main">
			<article>
				<h1>회원 가입</h1>
				<div class="member-lists">
					<ul>
						<li onclick="url('./members?grade=0')"><i class="xi-close-circle-o"></i> 탈퇴</li>
						<li onclick="url('./members?grade=1')"><i class="xi-minus-circle-o"></i> 강퇴</li>
						<li onclick="url('./members?grade=2')"><i class="xi-check-circle-o"></i> 정지</li>
						<li onclick="url('./members?grade=5')"><i class="xi-label-o"></i> 정상</li>
						<li onclick="url('./members?grade=9')"><i class="xi-plus-square-o"></i> 관리자</li>					
					</ul>
				</div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>가입일</th>
							<th>등급</th>
						</tr>
					</thead>
					<tbody>				
						<c:forEach var="co" items="${list }">
							<tr class="row row${co.mgrade }">
								<td class="d1">${co.mno }</td>
								<td class="title">${co.mid }</td>
								<td class="d1">${co.mname }</td>
								<td class="d2">${co.mdate }</td>
								<td class="d1">
									<select name="grade">
										<optgroup label="정지">
											<option <c:if test="${co.mgrade eq 0 }">selected="selected"</c:if> value="0">0 강퇴</option>
											<option <c:if test="${co.mgrade eq 1 }">selected="selected"</c:if> value="1">1 탈퇴</option>
											<option <c:if test="${co.mgrade eq 2 }">selected="selected"</c:if> value="2">2 정지</option>
										</optgroup>
										<optgroup label="정상">
											<option <c:if test="${co.mgrade eq 5 }">selected="selected"</c:if> value="5">5 평민</option>										
										</optgroup>
										<optgroup label="관리자">
											<option <c:if test="${co.mgrade eq 9 }">selected="selected"</c:if> value="9">9 관리자</option>										
										</optgroup>
									</select>
									${co.mgrade }
								</td>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>