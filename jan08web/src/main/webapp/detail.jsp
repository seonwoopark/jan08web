<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<link rel="stylesheet" type="text/css" href="./css/detail.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$(".commentEdit").click(function(){
			
			//let comment = $(this).parent().parent().siblings();
			//comment.hide();
			//alert(comment.html());
			let e =$(this).closest(".comment").children(".ccomment");
			
			alert(e.html());
			
			let cno = $(this).parent().children(".cno").val();
			//alert(cno);
		});
		
		// 댓글 버튼을 누르면 댓글창 나오게 하기 
		$(".commentDelete").click(function(){
			
			if(confirm("삭제하시겠습니까?")){
				
				//alert("삭제버튼을 눌렀습니다.");
				// 부모객체 찾아가기 = this ========= this() super()
				//$(this).parent(".cname").text("변경가능");
				//let cno = $(this).parent(".cname").children(".cno").val();
				
				let cno = $(this).parent().children(".cno").val();
				//ajax
				
				let point = $(this).closest(".comment");
				$.ajax({
					url:'./commentDel',
					type:'post',
					data:{ no : cno},
					dataType: 'text',
					success: function(result){
						//alert("서버에서 온 값 : "+result);
						
						if(result == 1){
							
							point.remove();
							//$(this).parents(".comment").remove();
						} else {
							alert("삭제할 수 없습니다. 관리자에게 문의하세요.");
						}
					},
					error: function(request, status, error){
						alert("문제가 발생했습니다.");
					}
				});
			}
			
		})
		
		$(".comment-write").hide();
		
		$(".xi-comment-o").click(function(){
			$(".xi-comment-o").hide();
			//$(".comment-write").show();
			$(".comment-write").slideToggle('slow');
		});
		
		
		$("#comment-btn").click(function(){
			let content = $("#commentcontent").val();
			let bno = ${detail.board_no};
			// 가상 form 만들기
			
			if(content.length >5){
				let form = $('<form></form>');
				form.attr('name','form');
				form.attr('method','post');
				form.attr('action','./comment');

				form.append($('<input/>',{type:'hidden',name:'commentcontent',value:content}));
				form.append($('<input/>',{type:'hidden',name:'no',value:bno}));
				
				form.appendTo("body");
				form.submit();
				/* let form = document.createElement('form');
				form.name='form';
				form.method='post';
				form.action='./comment';
				//붙이기
				let text = document.createElement('input');
				text.setAttribute("type","hidden");
				text.setAttribute("name","commentcontent");
				text.setAttribute("value",content);

				let no = document.createElement('input');
				no.setAttribute("type","hidden");
				no.setAttribute("name","no");
				no.setAttribute("value",bno);
				//form에 붙이기
				form.appendChild(text);
				form.appendChild(no);
				
				document.body.appendChild(form);
				form.submit(); */
				
			} else{
				alert("댓글은 다섯글자 이상으로 적어주세요.");
				$("#commentcotent").focus();
			}
		})
	})
</script>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="menu.jsp"/>
		</header>
	 	<div class="main">
			<div class="mainStyle">
				<article>
					<div class="detailDIV">
						<div class="detailTITLE">
							${detail.board_title }
						</div>
						<div class="detailWRITECOUNT">
							<div class="detailWRITE">${detail.board_write }
							
								<c:if test="${sessionScope.mid ne null && detail.mid eq sessionScope.mid }">
									<img onclick="update()" src="./img/update.png" alt="사진이 없습니다." title="누르면 수정하기">
									<img onclick="del()" src="./img/delete.png" alt="사진이 없습니다." title="누르면 삭제하기">
								</c:if>
							
							</div>
							<div class="detailCOUNT">${detail.ip} / ${detail.board_count }</div>
						</div>
						<div class="detailCONENT">
							${detail.board_content }
						</div>
					</div>
					
					<c:if test="${sessionScope.mid ne null }">
						<button class="xi-comment-o">댓글쓰기</button>
						<div class="comment-write">
							<div class="comments-form">
								<textarea name="commentcontent" id="commentcontent"></textarea>
								<button id="comment-btn">댓글쓰기</button>
							</div>
						</div>
					</c:if>
					
					
						<div class="comments">
							<c:forEach items="${list }" var="i">
								<c:if test="${i.cdel eq 1 }">
								<div class="comment">
									<div class="chead">
										<div class="cname">${i.mname} 님 
											<c:if test="${sessionScope.mid ne null && i.mid eq sessionScope.mid }">
												<input type="hidden" class="cno" value="${i.cno }">
												<img class="commentEdit" src="./img/update.png" alt="사진이 없습니다." title="누르면 수정하기">
												<img class="commentDelete" src="./img/delete.png" alt="사진이 없습니다." title="누르면 삭제하기">
											</c:if>
										</div>
										<div class="cdate">${i.ip} / ${i.cdate }</div>
									</div>
									<div class="ccomment">${i.ccomment }</div>
								</div>
								</c:if>
							</c:forEach>
						</div>
					
					<button onclick="url('./board?page=${param.page}')">게시판으로</button>
				</article>
			</div>
	 	<article></article>
	 	<article></article>
	 	<article></article>
	 	</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
 	</div>
 	<script type="text/javascript">
 	
 		function commentDel(cno){
 			if(confirm("댓글을 삭제하시겠습니까?")){
 				location.href="./commentDel?no=${detail.board_no}&cno="+cno;
 			}
 		}
 	
 		function commentUpdate(){
 			
 		}
 	
 		function del(){
 			let ch = confirm("삭제하시겠습니까");
 			if(ch){
 				location.href="./delete?no=${detail.board_no}";
 			}
 		}
 		
 		function update(){
 			let ch = confirm("수정하시겠습니까?");
 			if(ch){
 				location.href="./update?no=${detail.board_no}";
 			}
 		}
 	</script>
</body>
</html>