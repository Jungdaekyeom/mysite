<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navigation">
	<ul>
		<!-- c:set 연습용 주이 바보 -->
		<c:set var="admin" value="ADMIN" />
		<c:set var="user" value="USER" />

		<c:choose>
			<c:when test="${authUser.role eq admin}">
				<li><a href="${pageContext.request.contextPath }/admin">관리자페이지</a></li>
			</c:when>
			<c:when test="${authUser.role eq user}">
				<li><a href="${pageContext.request.contextPath }">${authUser.name}</a></li>
			</c:when>
		</c:choose>

		<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/spa">방명록(SPA)</a></li>
		<li><a href="${pageContext.request.contextPath }/board?p=1&sec=1">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
	</ul>
</div>