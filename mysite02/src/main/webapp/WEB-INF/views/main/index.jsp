<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href=<%=request.getContextPath() %>"assets/css/main.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="">로그인</a>
				<li>
				<li><a href="">회원가입</a>
				<li>
				<li><a href="">회원정보수정</a>
				<li>
				<li><a href="">로그아웃</a>
				<li>
				<li>님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile"
						src="https://www.google.com/maps/uv?pb=!1s0x356894c783efec33:0x6a11167b30803d73!3m1!7e115!4shttps://lh5.googleusercontent.com/p/AF1QipMu_EBxp6SGNg0C7_U2ookle_FZBTX_c0aeTqyV%3Dw355-h200-k-no!5z64-Z7J246rOg65Ox7ZWZ6rWQIC0gR29vZ2xlIOqygOyDiQ!15zQ2dJZ0FRPT0&imagekey=!1e10!2sAF1QipMu_EBxp6SGNg0C7_U2ookle_FZBTX_c0aeTqyV&hl=ko&sa=X&ved=2ahUKEwjYvqvP5MPzAhUSfd4KHY38B14Qoip6BAhhEAM"
						style='width: 100px'>
					<h2>안녕하세요. 안대혁의 mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br> 메뉴는 사이트 소개, 방명록, 게시판이 있구요.
						JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트
						입니다.<br>
						
						<!-- 이 부분이 과제에 해당됨 -->
						<br> <a href="<%=request.getContextPath() %>/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="<%=request.getContextPath() %>">정대겸</a></li>
				<li><a href="<%=request.getContextPath() %>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath() %>/board">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018, 2019, 2020, 2021</p>
		</div>
	</div>
</body>
</html>