<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<!-- 이 안의 script는 절대로 안에 아무것도 넣지 않을 것! -->
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!-- 
<script>
$(function(){
	$("#btn-check-email").click(function() {
		var email = $("#email").val();
		if(email == '') {
			return;
		}
		
		console.log(email);
		$.ajax({
			url: "${pageContext.request.contextPath }/user/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			success: function(response) {
				console.log(response);
			}
		});		
	});
	
});

$(function(){
	$("#btn-check-email").click(function){
		var email = $("email").var();
		if(email == ''){
			return;
		}
		
		console.log(email);
		
		$.ajax({
			url:"${pageContext.request.contextPath }//user/checkemail?email="+email,
			type:"get",
			dataType:"json",
			succeess: fuction(response){
				console.log(response);
			}
		})
	});
});

	setTimeout(function() {
		// ajax o = {message : "Hello World"}
		// $.ajax("", "post", "", function(), ...) 대신 다음과 같이 만듬
		option = {
			url : "/mysite03/hello",
			type : "get",
			dataType : "json",
			success : function(response) {
				console.log(response);
				p = $("#test");
				p.html("<strong>" + response.message + "</Strong>");
			}
		};
		
		option = {
				url : "/mysite03/msg02",
				type : "get",
				dataType : "json"
				success : function(response) {
					console.log(response);
					p = $("#test");
					p.html("<strong>" + response.message + "</Strong>");
				}
			};

		o = {
			message : "Hello World"
		}
		p = $("#test");
		p.html("<strong>Hello World</Strong>");

	}, 3000)

	setTimeout(function() {
		// 밑에 <p> 태그의 id를 test로 설정 후 3초 뒤 해당 함수를 실행
		e = document.getElementById("test2");
		// e를 구해서, e 안에 html을 넣어라.
		e.innerHTML = "<strong>Hello World</Strong>";
	}, 4000)

	// 함수를 파라미터로 전달한다!
	f = function() {
		console.log("!!!");
	}
	// 3초 후에 해당 코드를 실행한다.
	setTimeout(f, 3000);

	// F12의 console에서 확인가능
	for (i = 0; i < 5; i++) {
		console.log("Hello World : " + i);
	}
</script>
-->
<script>
$(function(){
	$("#btn-check-email").click(function() {
		var email = $("#email").val();
		if(email == '') {
			return;
		}
		
		console.log(email);
		$.ajax({
			url: "${pageContext.request.contextPath }/user/api/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			// 통신 에러 체크
			error: function(xhr, status, e){
				console.log(status, e);
				
			},
			success: function(response) {
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					
					return;
				}
				
				// UserController와 상호작용함
				if(response.data) {
					alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
					$("#email").val("").focus();
					return;
				}
				
				$("#btn-check-email").hide();
				$("#img-check-email").show();
			}
		});		
	});	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-check-email" type="button" value="중복체크">
					<img id="img-check-email" src='${pageContext.request.contextPath }/assets/images/check.png' style='width:16px; display: none'/>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<p id="test">
		</p>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>