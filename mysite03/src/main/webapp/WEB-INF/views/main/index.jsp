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
<link href="${pageContext.request.contextPath }/assets/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile"
						src="${pageContext.request.contextPath }/assets/images/Luffy.jpg">
					<h2>안녕하세요. 정대겸의 mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는 정대겸이 죽어가는 과정입니다.<br> 메뉴는 서블릿 고문, 스프링 고문, 마이바티스 고문 등이 있규요.
						Java수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운게 너무 빨라서 울며 겨자먹기로 복습하면서 잠도 못 자고 만드..쿨럭... 살려주세요...<br>
						<br> <a href="${pageContext.request.contextPath }/guestbook">방명록</a>에
						글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>