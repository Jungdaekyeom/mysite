<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.douzone.mysite.vo.UserVo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">

			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn:replace(vo.contents, newline, "<br/>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath }/board?p=${p }&sec=${sec}">글목록</a>
					<a
						href="${pageContext.servletContext.contextPath }/board/write/1?no=${vo.no}&p=${p }&sec=${sec}">답글달기</a>
					<c:choose>
						<c:when test="${authUser.no eq vo.userNo }">
							<a
								href="${pageContext.servletContext.contextPath }/board/modify/${vo.no}?p=${p }&sec=${sec}">글수정</a>
							<a
								href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}?p=${p }&sec=${sec}">글삭제</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>