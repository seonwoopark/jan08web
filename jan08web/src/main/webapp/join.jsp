<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./css/index.css"/>
<link rel="stylesheet" type="text/css" href="./css/menu.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<style type="text/css">
	.id-alert,.name-alert,.pw-alert{
		background: yellow;
	}
	.alert{
		color:red;
	}
</style>
<script type="text/javascript">
	
	//글로벌 변수
	let idCheckBool = false;
	
	
	
	$(function(){ // 제이쿼리 시작문 = 제이쿼리 시작합니다.
		$('.id-alert, .name-alert, .pw-alert').hide();
		
		//onchange()
		//$("#id").change(function (){
		//	alert("!");
		//});
		
		$('#id').on("change keyup paste", function(){
			//alert("아이디 입력창 값이 변경 되었습니다.");
			$('.id-alert').show();
			$('.id-alert').html('<p class="alert">당신이 입력한 ID는 '+ $('#id').val()+'</p>');
			if($('#id').val().length >= 5){
				idCheck();
			} else {
				$('#joinBtn').attr("disabled","disabled");
			}
		})
	});
	
	function check(){
			
			let id = $("#id").val();
			
			if(id.length < 3 || id ==""){
				$("#id").focus();
				$('.id-alert').show();
				return false;
			}$('.id-alert').hide();
			
			if(!idCheck){
				alert("ID 검사를 먼저 실행해주세요");
				return false;
			}
			
			let name = $('#name').val();
			
			if(name.length < 3){
				alert("이름은 3글자 이상이어야 합니다.")
				$("#name").focus();
				$('.name-alert').show();
				return false;
			}
			$('.name-alert').hide();
			
			let pw1 = $('#pw1').val();
			let pw2 = $('#pw2').val();
			
			if(pw1.length<8){
				alert("암호는 8글자 이상이어야합니다.")
				$('#pw1').focus();
				$('.pw-alert').show();
				return false;
			}
			$('.pw-alert').hide();
			
			if(pw2.length <1) {
				alert("암호는 8글자 이상이어야합니다.")
				$('#pw2').focus();
				$('.pw-alert').show();
				return false;
			}
			$('.pw-alert').hide();
			
			if(pw1 !== pw2) {
				alert("암호가 일치하지 않습니다.");
				$('#pw2').val("");
				$('#pw2').focus();
				$('.pw-alert').show();
				return false;
			}
			$('.pw-alert').hide();
			
			return true;
		}
	
	
	function idCheck(){
		//alert('id검사를 눌렀습니다.');
		
		let id = $('#id').val();
		//const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\sㄱ-ㅎㅏ-ㅣ가-힣]/g;//한글+공백
		const regExp = /^[a-z0-9]{5,15}$/;
		
		if(id.length < 5 || !regExp.test(id)){
			//alert("아이디는 영문자 5글자 이상이어야 합니다.");
			
			$('.id-alert').html('<p class="alert">아이디는 영문자 5글자 이상이고 특수문자가 없어야합니다.</p>');
			
			$('#joinBtn').attr("disabled","disabled");
			$('#id').focus();
		} else {
			// AJAX = 1 페이징, 2 AJAX, 3 파일 업로드
			$.ajax({
				url:'./idCheck',		// 이동할 주소
				type: 'post',			// POST / GET
				dataType: 'text',		// 수신 타입
				data: {'id': id},		// 보낼 값
				success: function(result){ // 성공 시
					//alert("통신에 성공했습니다.");
					if(result == 1){
						//alert("이미 가입되어 있습니다.");
						$('.id-alert').append('<p class="alert">이미 가입되어있습니다.</p>');
						$('#joinBtn').attr("disabled","disabled"); // 비활성화 시키기
						$('#id').focus();
						idCheckBool=false;
					} else {
						//alert("가입할 수 있습니다. 다음을 계속 진행하세요.");

						$('.id-alert').append('<p class="alert">가입할 수 있습니다.</p>');
						$(".id-alert .alert").css("color","green");
						$('#joinBtn').removeAttr("disabled"); //비활성화 제거하기 = 활성화 시키기
						//$('#name').focus();
						idCheckBool=true;
					}
				},
				error: function(request, status, error){
					alert("문제가 발생했습니다.");
				}
			});
		}
		
		return false;
		
	}
</script>
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
					<div class="join-form">
						<h1>회원가입</h1>
						<div class="mx-5 p-3 bg-secondary text-white ">
							<form action="./join" method="post" onsubmit="return check()">
								<div class="input-group mb-2">
									<span class="input-group-text">아이디</span>
									<input class="form-control" type="text" id="id" name="id" placeholder="id를 입력하세요.">
									<button class="btn btn-success input-group-text" onclick="return idCheck()">ID검사</button>
								</div>
								<div class="input-group mb-2 id-alert">
									<p class="alert">올바른 아이디를 입력하세요.</p>
								</div>
								<div class="input-group mb-2">
									<span class="input-group-text">이&ensp;&ensp;름</span>
									<input class="form-control" type="text" id="name" name="name" placeholder="이름을 입력하세요.">
								</div>
								<div class="input-group mb-2 name-alert">
									<p class="alert">올바른 이름을 입력하세요.</p>
								</div>
								<div class="input-group mb-2">
									<span class="input-group-text">암&ensp;&ensp;호</span>
									<input class="form-control" type="password" id="pw1" name="pw1" placeholder="암호를 입력하세요.">
									<span class="input-group-text">암호 확인</span>
									<input class="form-control" type="password" id="pw2" name="pw2" placeholder="암호를 입력하세요.">
								</div>
								<div class="input-group mb-2 pw-alert">
									<p class="alert">올바른 암호을 입력하세요.</p>
								</div>
								<div class="input-group">
									<button class="btn btn-danger col-6" type="reset">초기화</button>
									<button id="joinBtn" class="btn btn-info col-6" type="submit" disabled="disabled">가입하기</button>
								</div>
							</form>
						</div>
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